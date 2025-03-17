package org.travel.insurance.core.underwriting.calculators.medical;

import org.mockito.Mock;
import org.mockito.InjectMocks;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import org.springframework.test.util.ReflectionTestUtils;

import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.travel.insurance.core.domain.MedicalRiskLimitLevel;
import org.travel.insurance.core.repositories.MedicalRiskLimitLevelRepository;
import org.travel.insurance.dto.v1.TravelCalculatePremiumRequestV1;

import java.util.Optional;
import java.math.BigDecimal;

@ExtendWith(MockitoExtension.class)
class CalculatorInsuranceLimitCoefficientTest {

    @Mock private MedicalRiskLimitLevelRepository repository;
    @InjectMocks private CalculatorInsuranceLimitCoefficient calculator;
    private static String level = "LEVEL1";

    @Test
    public void expectedSuccess(){
        var request = new TravelCalculatePremiumRequestV1();
        request.setMedicalRiskLimitLevel(level);
        setEnabledTrue();
        setMedicalLimitRisk(BigDecimal.TWO);
        assertEquals(BigDecimal.TWO, calculator.calculate(request));
    }

    @Test
    public void expectedSuccess2(){
        var request = new TravelCalculatePremiumRequestV1();
        setEnabledFalse();
        assertEquals(BigDecimal.ONE, calculator.calculate(request));
    }

    private void setEnabledTrue(){
        ReflectionTestUtils.setField(calculator, "medicalRiskLimitLevelEnabled", true);
    }

    private void setEnabledFalse(){
        ReflectionTestUtils.setField(calculator, "medicalRiskLimitLevelEnabled", false);
    }

    private void setMedicalLimitRisk(BigDecimal value){
        var medicalRisk = new MedicalRiskLimitLevel();
        medicalRisk.setCoefficient(value);

        when(repository.findByMedicalRiskLimitLevelIc(level))
                .thenReturn(Optional.of(medicalRisk));
    }

}