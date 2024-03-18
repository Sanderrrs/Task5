package com.example.task5.models.responses;

import lombok.*;

import java.util.ArrayList;
import java.util.List;

@ToString
@Getter
@Setter
public class ProductResponse {

    private ProductData data = new ProductData();

    private String errorMsg;

    @ToString
    @Getter
    @Setter
    public class ProductData {
        private int instanceId;
        private List<Integer> registerId = new ArrayList<>();
        private List<Integer> supplementaryAgreementId = new ArrayList<>();
    }
}