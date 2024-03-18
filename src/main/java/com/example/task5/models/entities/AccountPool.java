package com.example.task5.models.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "account_pool")
public class AccountPool {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private Integer id;

    @Basic @Getter @Setter
    @Column(name = "branch_code")
    private String branchCode;

    @Basic @Getter @Setter
    @Column(name = "currency_code")
    private String currencyCode;

    @Basic @Getter @Setter
    @Column(name = "mdm_code")
    private String mdmCode;

    @Basic @Getter @Setter
    @Column(name = "priority_code")
    private String priorityCode;

    @Basic @Getter @Setter
    @Column(name = "registry_type_code")
    private String registryTypeCode;

    @OneToMany(cascade = CascadeType.ALL)
    @Getter @Setter
    @JoinColumn(name = "account_pool_id")
    private List<AccountNumber> accounts = new ArrayList<>();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AccountPool that = (AccountPool) o;
        return Objects.equals(branchCode, that.branchCode) && Objects.equals(currencyCode, that.currencyCode) && Objects.equals(mdmCode, that.mdmCode) && Objects.equals(priorityCode, that.priorityCode) && Objects.equals(registryTypeCode, that.registryTypeCode) && Objects.equals(accounts, that.accounts);
    }

    @Override
    public int hashCode() {
        return Objects.hash(branchCode, currencyCode, mdmCode, priorityCode, registryTypeCode, accounts);
    }
}
