package com.example.task5.models.entities;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.*;

@NoArgsConstructor
@ToString
@Component
@Entity
@Table(name = "tpp_product")
@AllArgsConstructor
public class TppProduct {


    @Id @Getter @Setter
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

   /* @Getter @Setter
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "general_agreement_id", insertable = false, updatable = false)
    private int generalAgreementId;*/

    @Getter @Setter
    @ManyToOne @JoinColumn(name = "product_code_id", referencedColumnName = "internal_id")
    private TppRefProductClass productCodeId;

    @Getter @Setter
    @Column(name = "client_id")
    private int clientId;

    @Getter @Setter
    @Column(name = "type", columnDefinition = "VARCHAR(50)")
    ProductType type;

    @Basic @Getter @Setter
    @Column(name = "number")
    private String number;

    @Basic @Getter @Setter
    @Column(name = "priority")
    private Integer priority;

    @Basic @Getter @Setter
    @Column(name = "date_of_conclusion")
    private Date dateOfConclusion;

    @Basic @Getter @Setter
    @Column(name = "start_date_time")
    private Date startDateTime;

    @Basic @Getter @Setter
    @Column(name = "end_date_time")
    private Date endDateTime;

    @Basic @Getter @Setter
    @Column(name = "days")
    private Short days;

    @Basic @Getter @Setter
    @Column(name = "penalty_rate")
    private BigDecimal penaltyRate;

    @Basic @Getter @Setter
    @Column(name = "nso")
    private BigDecimal nso;

    @Basic @Getter @Setter
    @Column(name = "threshold_amount")
    private BigDecimal thresholdAmount;

    @Basic @Getter @Setter
    @Column(name = "interest_rate_type")
    private String interestRateType;

    @Basic @Getter @Setter
    @Column(name = "tax_rate")
    private BigDecimal taxRate;

    @Basic @Getter @Setter
    @Column(name = "reasone_close")
    private String reasonClose;

    @Basic @Getter @Setter
    @Column(name = "state")
    private String state;


    @OneToMany(mappedBy = "productId")
    private List<Agreement> agreements = new ArrayList<>();

    public void addAgreement(Agreement agreement){
        agreement.setProductId(this);
        agreements.add(agreement);
    }

    public Iterator<Agreement> getAgreements() {
        return agreements.iterator();
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (!(object instanceof TppProduct that)) return false;

        if (id != that.id) return false;
        if (clientId != that.clientId) return false;
        if (!Objects.equals(productCodeId, that.productCodeId))
            return false;
        if (type != that.type) return false;
        if (!Objects.equals(number, that.number)) return false;
        if (!Objects.equals(priority, that.priority)) return false;
        if (!Objects.equals(dateOfConclusion, that.dateOfConclusion))
            return false;
        if (!Objects.equals(startDateTime, that.startDateTime))
            return false;
        if (!Objects.equals(endDateTime, that.endDateTime)) return false;
        if (!Objects.equals(days, that.days)) return false;
        if (!Objects.equals(penaltyRate, that.penaltyRate)) return false;
        if (!Objects.equals(nso, that.nso)) return false;
        if (!Objects.equals(thresholdAmount, that.thresholdAmount))
            return false;
        if (!Objects.equals(interestRateType, that.interestRateType))
            return false;
        if (!Objects.equals(taxRate, that.taxRate)) return false;
        if (!Objects.equals(reasonClose, that.reasonClose)) return false;
        if (!Objects.equals(state, that.state)) return false;
        return Objects.equals(agreements, that.agreements);
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (productCodeId != null ? productCodeId.hashCode() : 0);
        result = 31 * result + clientId;
        result = 31 * result + (type != null ? type.hashCode() : 0);
        result = 31 * result + (number != null ? number.hashCode() : 0);
        result = 31 * result + (priority != null ? priority.hashCode() : 0);
        result = 31 * result + (dateOfConclusion != null ? dateOfConclusion.hashCode() : 0);
        result = 31 * result + (startDateTime != null ? startDateTime.hashCode() : 0);
        result = 31 * result + (endDateTime != null ? endDateTime.hashCode() : 0);
        result = 31 * result + (days != null ? days.hashCode() : 0);
        result = 31 * result + (penaltyRate != null ? penaltyRate.hashCode() : 0);
        result = 31 * result + (nso != null ? nso.hashCode() : 0);
        result = 31 * result + (thresholdAmount != null ? thresholdAmount.hashCode() : 0);
        result = 31 * result + (interestRateType != null ? interestRateType.hashCode() : 0);
        result = 31 * result + (taxRate != null ? taxRate.hashCode() : 0);
        result = 31 * result + (reasonClose != null ? reasonClose.hashCode() : 0);
        result = 31 * result + (state != null ? state.hashCode() : 0);
        result = 31 * result + (agreements != null ? agreements.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "TppProduct{" +
                "id=" + id +
                ", clientId=" + clientId +
                ", number='" + number + '\'' +
                ", priority=" + priority +
                ", dateOfConclusion=" + dateOfConclusion +
                ", startDateTime=" + startDateTime +
                ", endDateTime=" + endDateTime +
                ", days=" + days +
                ", penaltyRate=" + penaltyRate +
                ", nso=" + nso +
                ", thresholdAmount=" + thresholdAmount +
                ", interestRateType='" + interestRateType + '\'' +
                ", taxRate=" + taxRate +
                ", reasonClose='" + reasonClose + '\'' +
                ", state='" + state + '\'' +
                '}';
    }
}
