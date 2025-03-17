package org.travel.insurance.core.underwriting.calculators.medical;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Value;

import org.travel.insurance.core.domain.AgeCoefficient;
import org.travel.insurance.core.repositories.AgeCoefficientRepository;
import org.travel.insurance.dto.v1.TravelCalculatePremiumRequestV1;

import java.time.LocalDate;
import java.math.BigDecimal;
import java.time.temporal.ChronoUnit;

@Component
@RequiredArgsConstructor
public class CalculatorAgeCoefficient {

    @Value( "${medical.risk.age.coefficient}" )
    private Boolean medicalRiskAgeCoefficientEnabled;

    private final AgeCoefficientRepository repositoryAge;

    public BigDecimal calculate(TravelCalculatePremiumRequestV1 request){
        if(!medicalRiskAgeCoefficientEnabled)
            return BigDecimal.ONE;

        LocalDate birthDay = request.getPersonBirthDate();
        int dif = (int) ChronoUnit.YEARS.between(birthDay, LocalDate.now());

        return repositoryAge
                .findAgeCoefficient(dif)
                .map(AgeCoefficient::getCoefficient)
                .orElse(BigDecimal.ZERO);
    }

}
