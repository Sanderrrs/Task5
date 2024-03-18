package com.example.task5.models.entities;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Objects;

@NoArgsConstructor
@Entity
@Table(name = "tpp_product_register")
@Data
public class TppProductRegister {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private Integer id;

    @ManyToOne @JoinColumn(name = "product_id", referencedColumnName = "id")
    private TppProduct productId;

    @ManyToOne(fetch = FetchType.LAZY) @JoinColumn(name = "type", referencedColumnName = "value")
    private TppRefProductRegisterType registerType;

    @Basic
    @Column(name = "account_number")
    private String accountNum;

    @Basic
    @Column(name = "account")
    private Integer accountNumId;

    @Basic
    @Column(name = "currency_code")
    private String currency;

    @Basic
    @Column(name = "state", columnDefinition = "VARCHAR(50)")
    private AccountState state;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TppProductRegister that = (TppProductRegister) o;
        return id.equals(that.id) && Objects.equals(productId, that.productId) && Objects.equals(registerType, that.registerType) && Objects.equals(accountNum, that.accountNum) && Objects.equals(currency, that.currency) && Objects.equals(state, that.state);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, productId, registerType, accountNum, currency, state);
    }

    public TppProductRegister(TppProduct productId, TppRefProductRegisterType registerType, String accountNum, String currency, Integer accountNumId) {
        this.productId = productId;
        this.registerType = registerType;
        this.accountNum = accountNum;
        this.currency = currency;
        this.state = AccountState.OPEN;
        this.accountNumId = accountNumId;
    }

}
