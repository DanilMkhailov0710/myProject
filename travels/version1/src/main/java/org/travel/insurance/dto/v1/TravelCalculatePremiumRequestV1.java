package org.travel.insurance.dto.v1;

import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import com.fasterxml.jackson.annotation.JsonAlias;

import java.util.List;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TravelCalculatePremiumRequestV1 {

    private String personFirstName;
    private String personLastName;

    private LocalDate personBirthDate;
    private LocalDate agreementDateFrom;
    private LocalDate agreementDateTo;

    private String country;
    private String medicalRiskLimitLevel;

    @JsonAlias(value = "selected_risks")
    private List<String> selectedRisks;

}
