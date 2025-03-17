package org.travel.insurance.core.underwriting.calculators.medical;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Value;

import org.travel.insurance.core.domain.MedicalRiskLimitLevel;
import org.travel.insurance.core.repositories.MedicalRiskLimitLevelRepository;
import org.travel.insurance.dto.v1.TravelCalculatePremiumRequestV1;

import java.math.BigDecimal;

@Component
@RequiredArgsConstructor
public class CalculatorInsuranceLimitCoefficient {

    @Value( "${medical.risk.limit.level.enabled}" )
    private Boolean medicalRiskLimitLevelEnabled;

    private final MedicalRiskLimitLevelRepository repository;

    public BigDecimal calculate(TravelCalculatePremiumRequestV1 request){
        if(!medicalRiskLimitLevelEnabled)
            return BigDecimal.ONE;

        var medicalLevel = request.getMedicalRiskLimitLevel();

        return repository
                .findByMedicalRiskLimitLevelIc(medicalLevel)
                .map(MedicalRiskLimitLevel::getCoefficient)
                .orElse(BigDecimal.ONE);
    }

}
