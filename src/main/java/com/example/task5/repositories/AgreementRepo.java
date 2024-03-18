package com.example.task5.repositories;

import com.example.task5.models.entities.Agreement;
import org.springframework.data.jpa.repository.JpaRepository;


public interface AgreementRepo extends JpaRepository<Agreement, Integer> {
}
