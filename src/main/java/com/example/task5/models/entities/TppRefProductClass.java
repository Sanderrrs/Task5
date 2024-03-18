package com.example.task5.models.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Objects;

@Entity
@Table(name = "tpp_ref_product_class")
@Getter
@Setter
public class TppRefProductClass {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "internal_id")
    private int id;

    @Basic
    @Column(name = "value")
    private String value;

    @Basic
    @Column(name = "gbi_code")
    private String gbiCode;

    @Basic
    @Column(name = "gbi_name")
    private String gbiName;

    @Basic
    @Column(name = "product_row_code")
    private String productRowCode;

    @Basic
    @Column(name = "product_row_name")
    private String productRowName;

    @Basic
    @Column(name = "subclass_code")
    private String subclassCode;

    @Basic
    @Column(name = "subclass_name")
    private String subclassName;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TppRefProductClass that = (TppRefProductClass) o;
        return id == that.id && Objects.equals(value, that.value) && Objects.equals(gbiCode, that.gbiCode) && Objects.equals(gbiName, that.gbiName) && Objects.equals(productRowCode, that.productRowCode) && Objects.equals(productRowName, that.productRowName) && Objects.equals(subclassCode, that.subclassCode) && Objects.equals(subclassName, that.subclassName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, value, gbiCode, gbiName, productRowCode, productRowName, subclassCode, subclassName);
    }
}
