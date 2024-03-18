package com.example.task5.servicies;

import com.example.task5.models.entities.AccountNumber;
import com.example.task5.models.entities.AccountPool;
import com.example.task5.models.entities.TppRefProductRegisterType;
import com.example.task5.repositories.AccountPoolRepo;
import jakarta.persistence.NoResultException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccountNumberService{

    private AccountPoolRepo accountPoolRepo;

    public AccountNumber getAccountNum(String branchCode, String currencyCode, String mdmCode, TppRefProductRegisterType registerType) {
        AccountPool accountPool = accountPoolRepo.getByBranchCodeAndCurrencyCodeAndMdmCodeAndRegistryTypeCode(
                branchCode,
                currencyCode,
                mdmCode,
                registerType.getValue()
        );

        if (accountPool == null){
            throw new NoResultException("Не найден пул счетов для продукта");
        }

        if (accountPool.getAccounts().stream().allMatch(AccountNumber::getBusy)) {
            throw new NoResultException("В пуле счетов закончились счета");
        }

        var accountNumber = accountPool.getAccounts().stream()
                .filter(x->!x.getBusy())
                .findFirst()
                .get();

        accountNumber.setBusy(true);

        return accountNumber;
    }

    @Autowired
    public void setAccountPoolRepo(AccountPoolRepo accountPoolRepo) {
        this.accountPoolRepo = accountPoolRepo;
    }
}
