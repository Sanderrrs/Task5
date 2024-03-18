package com.example.task5.repositories;

import com.example.task5.models.entities.TppRefProductClass;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductClassRepo  extends JpaRepository<TppRefProductClass, Integer> {
    TppRefProductClass getByValue(String productCode);
}
