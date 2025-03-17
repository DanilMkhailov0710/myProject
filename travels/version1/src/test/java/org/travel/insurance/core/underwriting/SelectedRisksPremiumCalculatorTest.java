package org.travel.insurance.core.underwriting;

import org.mockito.Mock;
import org.mockito.InjectMocks;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;

import org.travel.insurance.dto.v1.TravelCalculatePremiumRequestV1;

import java.util.List;
import java.math.BigDecimal;

@ExtendWith(MockitoExtension.class)
class SelectedRisksPremiumCalculatorTest {

    @Mock TravelRiskPremiumCalculator calculator1;
    @Mock TravelRiskPremiumCalculator calculator2;
    @InjectMocks SelectedRisksPremiumCalculator calculator;

    private String nameFiled = "travelRiskPremiumCalculators";

    @Test
    public void first(){
        var request = new TravelCalculatePremiumRequestV1();
        request.setSelectedRisks(List.of("Risk1", "Risk2"));
        when(calculator1.getRiskIc()).thenReturn("Risk1");
        when(calculator1.calculatePremium(request)).thenReturn(BigDecimal.ONE);
        when(calculator2.getRiskIc()).thenReturn("Risk2");
        when(calculator2.calculatePremium(request)).thenReturn(BigDecimal.ONE);
        var calculators = List.of(calculator1, calculator2);
        ReflectionTestUtils.setField(calculator, nameFiled, calculators);

        assertEquals(2, calculator.getSelectedRisks(request).size());
    }
}