package org.travel.insurance.core.underwriting.calculators.medical;

import org.mockito.Mock;
import org.mockito.InjectMocks;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.travel.insurance.dto.v1.TravelCalculatePremiumRequestV1;

import java.math.BigDecimal;

@ExtendWith(MockitoExtension.class)
class TravelRiskPremiumCalculatorMedicalTest {

    @Mock private CalculatorDaysBetween calculatorDays;
    @Mock private CalculatorDefaultDayPremium calculatorDefaultDay;
    @Mock private CalculatorAgeCoefficient calculatorAge;
    @Mock private CalculatorInsuranceLimitCoefficient calculatorInsurance;
    @InjectMocks private TravelRiskPremiumCalculatorMedical calculator;

    @Test
    public void simpleCheckByMultiply(){
        var request = new TravelCalculatePremiumRequestV1();
        settings(2, 5, 3, 4, request);
        assertEquals(new BigDecimal(120), calculator.calculatePremium(request));
    }

    @Test
    public void simpleCheckByMultiply2(){
        var request = new TravelCalculatePremiumRequestV1();
        settings(1, 2, 3, 4, request);
        assertEquals(new BigDecimal(24), calculator.calculatePremium(request));
    }

    @Test
    public void simpleCheckByMultiplyZero(){
        var request = new TravelCalculatePremiumRequestV1();
        settings(0, 2, 3, 4, request);
        assertEquals(new BigDecimal(0), calculator.calculatePremium(request));
    }

    private void settings(int a, int b, int c, int d, TravelCalculatePremiumRequestV1 request){
        when(calculatorDays.calculate(request)).thenReturn(new BigDecimal(a));
        when(calculatorDefaultDay.calculate(request)).thenReturn(new BigDecimal(b));
        when(calculatorAge.calculate(request)).thenReturn(new BigDecimal(c));
        when(calculatorInsurance.calculate(request)).thenReturn(new BigDecimal(d));
    }

}