package org.travel.insurance.dto.v2;

import lombok.Getter;
import lombok.Setter;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import org.travel.insurance.dto.Risk;
import org.travel.insurance.dto.util.BigDecimalSerialize;

import java.util.List;
import java.time.LocalDate;
import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PersonResponse {

    private String personFirstName;
    private String personLastName;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate personBirthDate;

    private String medicalRiskLimitLevel;

    @JsonSerialize(using = BigDecimalSerialize.class)
    private BigDecimal personPremium;

    @JsonAlias("person_risks")
    private List<Risk> personRisks;

}
