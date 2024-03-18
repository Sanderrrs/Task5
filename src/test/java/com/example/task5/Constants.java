package com.example.task5;


import com.example.task5.models.responses.ProductResponse;
import lombok.SneakyThrows;
import org.springframework.core.io.ClassPathResource;

import java.nio.charset.StandardCharsets;

public class Constants {


    public static String incorrectAccountRequest(){
        return "";
    }
    public static String correctAccountRequest(){
        return "{\n" +
                "\t\"instanceId\": \"1\",\n" +
                "\t\"registryTypeCode\": \"03.012.002_47533_ComSoLd\",\n" +
                "\t\"currencyCode\": \"USD\",\n" +
                "\t\"branchCode\": \"002\",\n" +
                "\t\"priorityCode\": \"00\",\n" +
                "\t\"mdmCode\": \"02\"\n" +
                "}";
    }
    public static String incorrectInstanceRequest(){
        return "";
    }
    public static String correctProductRequest(){
        return "{\n" +
                "\"instanceId\": null,\n" +
                "\"productType\": \"НСО\",\n" +
                "\"productCode\": \"02.001.005\",\n" +
                "\"registerType\": \"registerTypeValue\",\n" +
                "\"mdmCode\": \"03\",\n" +
                "\"contractNumber\": \"123456\",\n" +
                "\"contractDate\": \"2024-01-01T01:02:03\",\n" +
                "\"priority\": 1,\n" +
                "\"thresholdAmount\": 1000.11,\n" +
                "\"rateType\": \"0\",\n" +
                "\"taxPercentageRate\": 13.00,\n" +
                "\"contractId\": 9999,\n" +
                "\"branchCode\": \"003\",\n" +
                "\"isoCurrencyCode\": \"RUB\",\n" +
                "\"urgencyCode\": \"00\",\n" +
                "\"instanceArrangement\": [\n" +
                "{\n" +
                "\"generalAgreementId\": \"12345\",\n" +
                "\"supplementaryAgreementId\": \"67890\",\n" +
                "\"arrangementType\": \"arrangementTypeValue\",\n" +
                "\"shedulerJobId\": \"999\",\n" +
                "\"number\": \"contract1\",\n" +
                "\"openingDate\": \"2024-01-01T00:00:00\",\n" +
                "\"closingDate\": \"2025-01-01T00:00:00\",\n" +
                "\"cancelDate\": null,\n" +
                "\"validityDuration\": 5,\n" +
                "\"cancellationReason\": \"cancellationReasonValue\",\n" +
                "\"status\": \"открыт\",\n" +
                "\"interestCalculationDate\": \"2027-01-01T00:00:00\",\n" +
                "\"interestRate\": 9.55,\n" +
                "\"coefficient\": 1.00,\n" +
                "\"coefficientAction\": \"+-\",\n" +
                "\"minimumInterestRate\": 1.00,\n" +
                "\"minimumInterestRateCoefficient\": 0.5,\n" +
                "\"minimumInterestRateCoefficientAction\": \"+-\",\n" +
                "\"maximalnterestRate\": 2.00,\n" +
                "\"maximalnterestRateCoefficient\": 0.7,\n" +
                "\"maximalnterestRateCoefficientAction\": \"-+\"\t\n" +
                "},\n" +
                "{\n" +
                "\"generalAgreementId\": \"12345\",\n" +
                "\"supplementaryAgreementId\": \"67890\",\n" +
                "\"arrangementType\": \"arrangementTypeValue\",\n" +
                "\"shedulerJobId\": \"999\",\n" +
                "\"number\": \"contract2\",\n" +
                "\"openingDate\": \"2024-01-01T00:00:00\",\n" +
                "\"closingDate\": \"2025-01-01T00:00:00\",\n" +
                "\"cancelDate\": null,\n" +
                "\"validityDuration\": 5,\n" +
                "\"cancellationReason\": \"cancellationReasonValue\",\n" +
                "\"status\": \"открыт\",\n" +
                "\"interestCalculationDate\": \"2027-01-01T00:00:00\",\n" +
                "\"interestRate\": 9.55,\n" +
                "\"coefficient\": 1.00,\n" +
                "\"coefficientAction\": \"+-\",\n" +
                "\"minimumInterestRate\": 1.00,\n" +
                "\"minimumInterestRateCoefficient\": 0.5,\n" +
                "\"minimumInterestRateCoefficientAction\": \"+-\",\n" +
                "\"maximalnterestRate\": 2.00,\n" +
                "\"maximalnterestRateCoefficient\": 0.7,\n" +
                "\"maximalnterestRateCoefficientAction\": \"-+\"\t\n" +
                "}\n" +
                "]\n" +
                "}";
    }

    public static ProductResponse correctProductResponse(){
        var productResponse = new ProductResponse();

        productResponse.getData().getRegisterId().add(2);

        productResponse.getData().setInstanceId(2);
        return productResponse;
    }

    @SneakyThrows
    static String fromFile(String path) {
        return new String(new ClassPathResource(path).getInputStream().readAllBytes(), StandardCharsets.UTF_8);
    }
}
