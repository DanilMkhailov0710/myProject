package org.travel.insurance.core.api.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.List;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PersonDTO {

    private String personFirstName;

    private String personLastName;

    private LocalDate personBirthDate;

    private String medicalRiskLimitLevel;

    private List<RiskDTO> risks;

}
