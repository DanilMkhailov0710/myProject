package org.travel.insurance.dto.v2;

import lombok.Getter;
import lombok.Setter;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

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
public class TravelCalculatePremiumResponseV2 extends CoreResponse {

    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate agreementDateFrom;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate agreementDateTo;

    private String country;

    @JsonSerialize(using = BigDecimalSerialize.class)
    private BigDecimal agreementPremium;

    private List<PersonResponse> persons;

    public TravelCalculatePremiumResponseV2(List<ValidationError> errors) {
        super(errors);
    }

}
