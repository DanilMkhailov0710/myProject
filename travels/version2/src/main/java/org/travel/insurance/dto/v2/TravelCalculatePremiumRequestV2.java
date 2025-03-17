package org.travel.insurance.dto.v2;

import lombok.Getter;
import lombok.Setter;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.List;
import java.time.LocalDate;

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

    @JsonAlias(value = "selected_risks")
    private List<String> selectedRisks;

    private List<PersonRequest> persons;

}
