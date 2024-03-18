package com.example.task5.repositories;

import com.example.task5.models.entities.TppRefProductRegisterType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRegisterTypeRepo extends JpaRepository<TppRefProductRegisterType, Integer> {

    List<TppRefProductRegisterType> findAllByProductClassCodeAndAccountType(String productClassCode, String accountType);

    List<TppRefProductRegisterType> findAllByProductClassCodeAndValue(String productClassCode, String registryTypeCode);

    TppRefProductRegisterType getByValue(String typeCode);
}
