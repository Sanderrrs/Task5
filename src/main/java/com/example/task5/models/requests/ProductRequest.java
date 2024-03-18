package com.example.task5.models.requests;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.validation.annotation.Validated;
import java.util.ArrayList;
import java.util.Date;
import java.util.Properties;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;

@Data
public class ProductRequest {
    Integer instanceId;
    @NotEmpty String productType;
    @NotEmpty String productCode;
    @NotEmpty String registerType;
    @NotEmpty String mdmCode;
    @NotEmpty String contractNumber;
    Date contractDate;
    Integer priority;
    Float interestRatePenalty;
    Float minimalBalance;
    Float thresholdAmount;
    String accountingDetails;
    String rateType;
    Float taxPercentageRate;
    Float technicalOverdraftLimitAmount;
    Integer contractId;
    @NotEmpty String branchCode;
    @NotEmpty String isoCurrencyCode;
    @NotEmpty String urgencyCode;
    Integer referenceCode;
    ArrayList<Properties> properties;
    ArrayList<AgreementRequest> arrangements;
}
