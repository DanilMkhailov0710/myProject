package org.travel.insurance.core.underwriting.calculators.medical;

import org.mockito.Mock;
import org.mockito.InjectMocks;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.travel.insurance.core.api.dto.PersonDTO;
import org.travel.insurance.core.api.dto.AgreementDTO;

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
        var agreement = new AgreementDTO();
        var person = new PersonDTO();
        settings(2, 5, 3, 4, agreement, person);
        assertEquals(new BigDecimal(120), calculator.calculatePremium(agreement, person));
    }

    @Test
    public void simpleCheckByMultiply2(){
        var agreement = new AgreementDTO();
        var person = new PersonDTO();
        settings(1, 2, 3, 4, agreement, person);
        assertEquals(new BigDecimal(24), calculator.calculatePremium(agreement, person));
    }

    @Test
    public void simpleCheckByMultiplyZero(){
        var agreement = new AgreementDTO();
        var person = new PersonDTO();
        settings(0, 2, 3, 4, agreement, person);
        assertEquals(new BigDecimal(0), calculator.calculatePremium(agreement, person));
    }

    private void settings(int a, int b, int c, int d, AgreementDTO request, PersonDTO person){
        when(calculatorDays.calculate(request)).thenReturn(new BigDecimal(a));
        when(calculatorDefaultDay.calculate(request)).thenReturn(new BigDecimal(b));
        when(calculatorAge.calculate(person)).thenReturn(new BigDecimal(c));
        when(calculatorInsurance.calculate(person)).thenReturn(new BigDecimal(d));
    }

}