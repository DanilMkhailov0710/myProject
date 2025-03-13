package org.travel.insurance.dto.v2;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TravelCalculatePremiumRequestV2 {

    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate agreementDateFrom;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate agreementDateTo;

    private String country;
    private String medicalRiskLimitLevel;

    @JsonAlias(value = "selected_risks")
    private List<String> selectedRisks;

    private List<PersonRequest> persons;

}
