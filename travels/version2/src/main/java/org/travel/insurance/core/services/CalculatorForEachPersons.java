package org.travel.insurance.core.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import org.travel.insurance.core.api.dto.AgreementDTO;
import org.travel.insurance.core.underwriting.TravelPremiumUnderwriting;

@Component
@RequiredArgsConstructor
class CalculatorForEachPersons {

    private final TravelPremiumUnderwriting premiumUnderwriting;

    void calculateRiskPremiumsForAllPersons(AgreementDTO agreement) {
        agreement.getPersons().forEach(person -> {
            var calculationResult = premiumUnderwriting.computePrice(agreement, person);
            person.setRisks(calculationResult.risks());
        });
    }

}
