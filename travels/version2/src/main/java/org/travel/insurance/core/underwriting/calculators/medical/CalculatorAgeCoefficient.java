package org.travel.insurance.core.underwriting.calculators.medical;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Value;

import org.travel.insurance.core.api.dto.PersonDTO;

import org.travel.insurance.core.domain.AgeCoefficient;
import org.travel.insurance.core.repositories.AgeCoefficientRepository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

@Component
@RequiredArgsConstructor
public class CalculatorAgeCoefficient {

    @Value( "${medical.risk.age.coefficient}" )
    private Boolean medicalRiskAgeCoefficientEnabled;

    private final AgeCoefficientRepository repositoryAge;

    public BigDecimal calculate(PersonDTO person){
        if(!medicalRiskAgeCoefficientEnabled)
            return BigDecimal.ONE;

        LocalDate birthDay = person.getPersonBirthDate();
        int dif = (int) ChronoUnit.YEARS.between(birthDay, LocalDate.now());

        return repositoryAge
                .findAgeCoefficient(dif)
                .map(AgeCoefficient::getCoefficient)
                .orElse(BigDecimal.ZERO);
    }

}
