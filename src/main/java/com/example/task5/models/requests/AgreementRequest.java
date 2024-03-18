package com.example.task5.models.requests;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.validation.constraints.NotEmpty;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AgreementRequest {
    String generalAgreementId;
    String supplementaryAgreementId;
    String arrangementType;
    Integer schedulerJobId;
    @NotEmpty
    String number;
    @NotEmpty String openingDate;
    String closingDate;
    String cancelDate;
    String validityDuration;
    String cancellationReason;
    String status;
    String interestCalculationDate;
    int interestRate;
    int coefficient;
    String coefficientAction;
    int minimumInterestRate;
    int minimumInterestRateCoefficient;
    String minimumInterestRateCoefficientAction;
    int maximalInterestRate;
    int maximalInterestRateCoefficient;
    String maximalInterestRateCoefficientAction;
}
