package org.travel.insurance.core.underwriting;

import org.mockito.Mock;
import org.mockito.InjectMocks;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.travel.insurance.core.api.dto.RiskDTO;
import org.travel.insurance.core.api.dto.PersonDTO;
import org.travel.insurance.core.api.dto.AgreementDTO;

import java.util.List;
import java.math.BigDecimal;

@ExtendWith(MockitoExtension.class)
class TravelPremiumUnderwritingImpTest {

    @Mock SelectedRisksPremiumCalculator calculatorSelectedRisks;
    @InjectMocks TravelPremiumUnderwritingImp calculatorPremiumPriceImp;

    private String nameFiled = "calculatorSelectedRisks";

    @Test
    public void first(){
        var agreement = new AgreementDTO();
        var person = new PersonDTO();
        RiskDTO risk1 = new RiskDTO("riscIc1", BigDecimal.ONE);
        RiskDTO risk2 = new RiskDTO("riscIc2", BigDecimal.ONE);

        var risks = List.of(risk1, risk2);
        when(calculatorSelectedRisks.getSelectedRisks(agreement, person))
                .thenReturn(risks);

        assertEquals(BigDecimal.TWO, calculatorPremiumPriceImp.computePrice(agreement, person).totalPremium());
    }

}