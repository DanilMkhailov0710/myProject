package org.travel.insurance.dto.v1;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import org.travel.insurance.dto.Risk;
import org.travel.insurance.dto.CoreResponse;
import org.travel.insurance.dto.ValidationError;
import org.travel.insurance.dto.util.BigDecimalSerialize;

import java.util.List;
import java.time.LocalDate;
import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TravelCalculatePremiumResponseV1 extends CoreResponse {

    private String personFirstName;
    private String personLastName;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate personBirthDate;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate agreementDateFrom;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate agreementDateTo;

    private String country;
    private String medicalRiskLimitLevel;

    @JsonSerialize(using = BigDecimalSerialize.class)
    private BigDecimal agreementPremium;

    private List<Risk> risks;

    public TravelCalculatePremiumResponseV1(List<ValidationError> errors) {
        super(errors);
    }

}
