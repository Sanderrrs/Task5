package com.example.task5.repositories;

import com.example.task5.models.entities.TppProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepo extends JpaRepository<TppProduct, Integer> {
    TppProduct getByNumber(String number);
}

