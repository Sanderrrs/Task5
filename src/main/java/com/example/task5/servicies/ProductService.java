package com.example.task5.servicies;

import com.example.task5.models.entities.ProductType;
import com.example.task5.models.entities.TppProduct;
import com.example.task5.models.entities.TppProductRegister;
import com.example.task5.models.requests.ProductRequest;
import com.example.task5.models.responses.ProductResponse;
import com.example.task5.repositories.*;
import jakarta.persistence.NoResultException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class ProductService {
    private final ProductRepo productRepo;

    private final ProductRegisterTypeRepo registerTypeRepo;
    private final ProductRegisterRepo registerRepo;
    private final AgreementRepo agreementsRepo;

    private final PlaceHolderService placeHolderService;
    private final ProductClassRepo productClassRepo;

    private final AccountNumberService accountNumberService;

    @Autowired
    public ProductService(ProductRepo productRepo, ProductRegisterTypeRepo registerTypeRepo, ProductRegisterRepo registerRepo, AgreementRepo agreementsRepo, PlaceHolderService placeHolderService, ProductClassRepo productClassRepo, AccountNumberService accountNumService) {
        this.productRepo = productRepo;
        this.registerTypeRepo = registerTypeRepo;
        this.registerRepo = registerRepo;
        this.agreementsRepo = agreementsRepo;
        this.placeHolderService = placeHolderService;
        this.productClassRepo = productClassRepo;
        this.accountNumberService = accountNumService;
    }
    public List<TppProduct> findAll() {
        return productRepo.findAll();
    }

    public TppProduct findOne(int id) {
        Optional<TppProduct> foundTppProduct = productRepo.findById(id);
        return foundTppProduct.orElse(null);
    }

    @Transactional
    public void save(TppProduct TppProduct) {
        productRepo.save(TppProduct);
    }

    @Transactional
    public void update(int id, TppProduct updatedTppProduct) {
        updatedTppProduct.setId(id);
        productRepo.save(updatedTppProduct);
    }

    @Transactional
    public void delete(int id) {
        productRepo.deleteById(id);
    }

    public TppProduct createProductEntity(ProductRequest productRequest) {
        var pe = new TppProduct();
        pe.setProductCodeId(productClassRepo.getByValue(productRequest.getProductCode()));
        pe.setClientId(placeHolderService.getClientId(productRequest.getMdmCode()));
        pe.setType(ProductType.valueOf(productRequest.getProductType()));
        pe.setNumber(productRequest.getContractNumber());
        pe.setPriority(productRequest.getPriority());
        pe.setDateOfConclusion(productRequest.getContractDate());
        pe.setStartDateTime(new Date());
        pe.setPenaltyRate(BigDecimal.valueOf(productRequest.getInterestRatePenalty()));
        pe.setNso(BigDecimal.valueOf(productRequest.getMinimalBalance()));
        pe.setThresholdAmount(BigDecimal.valueOf(productRequest.getThresholdAmount()));
        pe.setInterestRateType(productRequest.getRateType());
        pe.setTaxRate(BigDecimal.valueOf(productRequest.getTaxPercentageRate()));
        pe.setState("OPEN");
        return pe;
    }
    @Transactional
    public ProductResponse saveRequest(ProductRequest productRequest) {
        var productResponse = new ProductResponse();
        TppProduct productEntity;

        var productId = productRequest.getInstanceId();

        if (productId == null) {
            // Проверяем корректность переданного значения в поле ProductCode
            var registerTypes = registerTypeRepo.findAllByProductClassCodeAndAccountType(productRequest.getProductCode(), "Клиентский");
            if (registerTypes.isEmpty()) {
                throw new NoResultException("КодПродукта =\""+productRequest.getProductCode()+"\" не найден в Каталоге продуктов (tpp_ref_product_register_type)");
            }

            // Проверяем что нет договора с таким же номером
            var existProduct = productRepo.getByNumber(productRequest.getContractNumber());
            if (existProduct != null) {
                throw new IllegalArgumentException("Параметр ContractNumber \"№ договора\" "+productRequest.getContractNumber()+" уже существует для \n ЭП с ИД "+existProduct.getId());
            }

            // в теории можно вынести отдельно, но на текущий момент не вижу смысла
            productEntity = createProductEntity(productRequest);

            for (var registerType: registerTypes) {
                var accountNum = accountNumberService.getAccountNum(productRequest.getBranchCode(), productRequest.getIsoCurrencyCode(), productRequest.getMdmCode(), registerType);
                var productRegister = new TppProductRegister(productEntity, registerType, accountNum.getAccountNumber(), productRequest.getIsoCurrencyCode(),accountNum.getId());
                registerRepo.save(productRegister);
                productResponse.getData().getRegisterId().add(productRegister.getId());
            }
        } else {
            //
            var product = productRepo.findById(productId);
            // Проверяем что нашли продукт
            if (product.isEmpty()) {
                throw new IllegalArgumentException("Не найден договор соответствующий параметру instanceId \"Идентификатор экземпляра продукта\" = "+productRequest.getInstanceId());
            }
            productEntity = product.get();
        }
        productRepo.saveAndFlush(productEntity);

        // Дозаполняем ответ
        productResponse.getData().setInstanceId(productEntity.getId());

        return productResponse;
    }

}
