package org.travel.insurance.core.underwriting.calculators;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import org.travel.insurance.core.api.dto.PersonDTO;
import org.travel.insurance.core.api.dto.AgreementDTO;

import org.travel.insurance.core.underwriting.TravelRiskPremiumCalculator;

import java.math.BigDecimal;

@Component
@RequiredArgsConstructor
class TravelRiskPremiumCalculatorCancellation implements TravelRiskPremiumCalculator {

    @Override
    public BigDecimal calculatePremium(AgreementDTO agreement, PersonDTO person) {
        return BigDecimal.ZERO;
    }

    @Override
    public String getRiskIc() {
        return "TRAVEL_CANCELLATION";
    }

}
