package org.travel.insurance.core.underwriting.calculators.medical;

import org.mockito.Mock;
import org.mockito.InjectMocks;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import org.springframework.test.util.ReflectionTestUtils;

import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.travel.insurance.core.domain.AgeCoefficient;
import org.travel.insurance.core.repositories.AgeCoefficientRepository;
import org.travel.insurance.dto.v1.TravelCalculatePremiumRequestV1;

import java.util.Optional;
import java.time.LocalDate;
import java.math.BigDecimal;

@ExtendWith(MockitoExtension.class)
class CalculatorAgeCoefficientTest {

    @Mock private AgeCoefficientRepository repositoryAge;
    @InjectMocks private CalculatorAgeCoefficient calculator;

    @Test
    public void second(){
        var request = new TravelCalculatePremiumRequestV1();
        request.setPersonBirthDate(LocalDate.now().plusDays(1L));
        setEnabledTrue();
        assertEquals(BigDecimal.ZERO, calculator.calculate(request));
    }

    @Test
    public void secondFalse(){
        var request = new TravelCalculatePremiumRequestV1();
        setEnabledFalse();
        assertEquals(BigDecimal.ONE, calculator.calculate(request));
    }

    @Test
    public void second2(){
        AgeCoefficient coefficient = new AgeCoefficient();
        coefficient.setCoefficient(new BigDecimal(0.7));
        when(repositoryAge.findAgeCoefficient(0))
                .thenReturn(Optional.of(coefficient));
        var request = new TravelCalculatePremiumRequestV1();
        request.setPersonBirthDate(LocalDate.now().plusDays(-1L));
        setEnabledTrue();
        assertEquals(new BigDecimal(0.7), calculator.calculate(request));
    }

    private void setEnabledTrue(){
        ReflectionTestUtils.setField(calculator, "medicalRiskAgeCoefficientEnabled", true);
    }

    private void setEnabledFalse(){
        ReflectionTestUtils.setField(calculator, "medicalRiskAgeCoefficientEnabled", false);
    }
}