package com.example.task5.models.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Entity
@Table(name = "agreement")
@Getter
@Setter
public class Agreement {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private int id;

    @ManyToOne
    @JoinColumn(name = "product_id", referencedColumnName = "id")
    private TppProduct productId;

    @Basic
    @Column(name = "number")
    private String number;

    public Agreement(String number) {
        this.number = number;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Agreement that = (Agreement) o;
        return id == that.id;// && Objects.equals(agreementId, that.agreementId) && Objects.equals(number, that.number);
    }

    @Override
    public int hashCode() {
        return 1;//Objects.hash(id, agreementId, number);
    }
}
