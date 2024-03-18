package com.example.task5.models.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "account")
public class AccountNumber {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private Integer id;

    @Basic
    @Column(name = "account_pool_id", columnDefinition = "int(8)")
    private Long accountPoolId;

    @Basic
    @Column(name = "account_number")
    private String accountNumber;

    @Basic
    @Column(name = "bussy")
    private Boolean busy;
}
