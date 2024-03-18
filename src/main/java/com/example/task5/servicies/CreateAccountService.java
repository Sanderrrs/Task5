package com.example.task5.servicies;

import com.example.task5.models.entities.TppProduct;
import com.example.task5.models.entities.TppProductRegister;
import com.example.task5.models.entities.TppRefProductRegisterType;
import com.example.task5.models.requests.AccountRequest;
import com.example.task5.models.responses.CreateAccountResponse;
import com.example.task5.repositories.ProductRegisterRepo;
import com.example.task5.repositories.ProductRegisterTypeRepo;
import com.example.task5.repositories.ProductRepo;
import jakarta.persistence.NoResultException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service

public class CreateAccountService{



    private final ProductRegisterRepo registerRepo;
    private final ProductRepo productRepo;
    private final ProductRegisterTypeRepo registerTypeRepo;
    private final AccountNumberService accountNumService;

    @Autowired
    public CreateAccountService(ProductRegisterRepo registerRepo, ProductRepo productRepo, ProductRegisterTypeRepo registerTypeRepo, AccountNumberService accountNumService) {
        this.registerRepo = registerRepo;
        this.productRepo = productRepo;
        this.registerTypeRepo = registerTypeRepo;
        this.accountNumService = accountNumService;
    }

    public CreateAccountResponse createAccount(AccountRequest accountRequest) throws NoResultException {
        TppProduct productId;
        CreateAccountResponse accountResponse = new CreateAccountResponse();

        // Ищем экземпляр продукта по переданному Id
        if (productRepo.existsById(accountRequest.getInstanceId())) {
            productId = productRepo.getReferenceById(accountRequest.getInstanceId());
        } else {
            throw new NoResultException("По instanceId \"Идентификатор ЭП\" <"+accountRequest.getInstanceId()+"> не найден экземпляр продукта.");
        }

        // Проверяем на дубли
        TppRefProductRegisterType registerTypeEntity = registerTypeRepo.getByValue(accountRequest.getRegistryTypeCode());
       /* if (registerRepo.existsByProductIdAndRegisterType(productId, registerTypeEntity)) {
            throw new IllegalArgumentException("Параметр registryTypeCode \"Тип регистра\" <"+accountRequest.getRegistryTypeCode()+"> уже существует для ЭП с ИД <"+accountRequest.getInstanceId()+">.");
        }*/

        // Определяем тип регистра
        List<TppRefProductRegisterType> registerTypes = registerTypeRepo.findAllByProductClassCodeAndValue(productId.getProductCodeId().getValue(), accountRequest.getRegistryTypeCode());
        if (registerTypes.isEmpty()) {
            throw new NoResultException("КодПродукта <"+productId.getProductCodeId().getValue()+"> не найдено в Каталоге продуктов для данного типа Регистра \""+accountRequest.getRegistryTypeCode()+"\"");
        }

        // Получаем номер счёта
        var accountNum = accountNumService.getAccountNum(
                accountRequest.getBranchCode(),
                accountRequest.getCurrencyCode(),
                accountRequest.getMdmCode(),
                registerTypeEntity
        );

        // Создаём ПР
        TppProductRegister accountEntity = new TppProductRegister(productId, registerTypeEntity, accountNum.getAccountNumber(), accountRequest.getCurrencyCode(),accountNum.getId());

        registerRepo.save(accountEntity);

        accountResponse.getData().setAccountId (accountEntity.getId().toString());
        return accountResponse;
    }
}
