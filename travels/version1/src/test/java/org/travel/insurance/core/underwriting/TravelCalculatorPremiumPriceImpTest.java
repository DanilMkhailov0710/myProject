package org.travel.insurance.core.underwriting;

import org.mockito.Mock;
import org.mockito.InjectMocks;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.travel.insurance.dto.v1.TravelCalculatePremiumRequestV1;
import org.travel.insurance.dto.Risk;

import java.util.List;
import java.math.BigDecimal;

@ExtendWith(MockitoExtension.class)
class TravelCalculatorPremiumPriceImpTest {

    @Mock SelectedRisksPremiumCalculator calculatorSelectedRisks;
    @InjectMocks TravelCalculatorPremiumPriceImp calculatorPremiumPriceImp;

    private String nameFiled = "calculatorSelectedRisks";

    @Test
    public void first(){
        var request = new TravelCalculatePremiumRequestV1();
        Risk risk1 = new Risk("riscIc1", BigDecimal.ONE);
        Risk risk2 = new Risk("riscIc2", BigDecimal.ONE);

        var risks = List.of(risk1, risk2);
        when(calculatorSelectedRisks.getSelectedRisks(request))
                .thenReturn(risks);

        assertEquals(BigDecimal.TWO, calculatorPremiumPriceImp.computePrice(request).totalPremium());
    }
  
}