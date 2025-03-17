package org.travel.insurance.core.api.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.util.List;
import java.time.LocalDate;
import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AgreementDTO {

    private LocalDate agreementDateFrom;

    private LocalDate agreementDateTo;

    private String country;

    private List<String> selectedRisks;

    private List<PersonDTO> persons;

    private BigDecimal agreementPremium;

}
