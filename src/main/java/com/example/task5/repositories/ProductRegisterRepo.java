package com.example.task5.repositories;

import com.example.task5.models.entities.TppProduct;
import com.example.task5.models.entities.TppProductRegister;
import com.example.task5.models.entities.TppRefProductRegisterType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRegisterRepo extends JpaRepository<TppProductRegister, Integer> {
    boolean existsByProductIdAndRegisterType(TppProduct productId, TppRefProductRegisterType registerType);
    TppProductRegister getByProductIdAndRegisterType(TppProduct productId, TppRefProductRegisterType registerType);
}
