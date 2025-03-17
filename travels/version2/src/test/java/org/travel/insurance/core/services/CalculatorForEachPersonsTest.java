package org.travel.insurance.core.services;

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

import org.travel.insurance.core.underwriting.TravelPremiumUnderwriting;
import org.travel.insurance.core.underwriting.TravelStoragePremiumSelectedRisks;

import java.util.List;
import java.math.BigDecimal;

@ExtendWith(MockitoExtension.class)
class CalculatorForEachPersonsTest {

    @Mock private TravelPremiumUnderwriting premiumUnderwriting;
    @InjectMocks private CalculatorForEachPersons calculator;

    @Test
    public void shouldCalculateRiskPremiumForAllPersons() {
        PersonDTO person1 = new PersonDTO();
        PersonDTO person2 = new PersonDTO();

        AgreementDTO agreement = new AgreementDTO();
        agreement.setPersons(List.of(person1, person2));

        RiskDTO risk11 = new RiskDTO("RISK_1", BigDecimal.ONE);
        RiskDTO risk12 = new RiskDTO("RISK_2", BigDecimal.ONE);
        RiskDTO risk21 = new RiskDTO("RISK_3", BigDecimal.ONE);
        RiskDTO risk22 = new RiskDTO("RISK_4", BigDecimal.ONE);

        var calculationResult1 = new TravelStoragePremiumSelectedRisks(BigDecimal.ONE, List.of(risk11, risk12));
        var calculationResult2 = new TravelStoragePremiumSelectedRisks(BigDecimal.ONE, List.of(risk21, risk22));
        when(premiumUnderwriting.computePrice(agreement, person1)).thenReturn(calculationResult1);
        when(premiumUnderwriting.computePrice(agreement, person2)).thenReturn(calculationResult2);

        calculator.calculateRiskPremiumsForAllPersons(agreement);

        assertEquals(agreement.getPersons().get(0).getRisks().size(), 2);
        assertEquals(agreement.getPersons().get(1).getRisks().size(), 2);
    }

}