package com.example.task5.repositories;

import com.example.task5.models.entities.AccountPool;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountPoolRepo  extends JpaRepository<AccountPool, Integer> {
    AccountPool getByBranchCodeAndCurrencyCodeAndMdmCodeAndRegistryTypeCode(
            String branchCode,
            String currencyCode,
            String mdmCode,
            String registryTypeCode
    );
}
