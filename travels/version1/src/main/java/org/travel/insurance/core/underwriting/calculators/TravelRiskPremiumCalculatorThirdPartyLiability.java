package org.travel.insurance.core.underwriting.calculators;

import lombok.RequiredArgsConstructor;
import org.travel.insurance.core.underwriting.TravelRiskPremiumCalculator;
import org.travel.insurance.dto.v1.TravelCalculatePremiumRequestV1;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
@RequiredArgsConstructor
class TravelRiskPremiumCalculatorThirdPartyLiability implements TravelRiskPremiumCalculator {

    @Override
    public BigDecimal calculatePremium(TravelCalculatePremiumRequestV1 request) {
        return BigDecimal.ZERO;
    }

    @Override
    public String getRiskIc() {
        return "TRAVEL_THIRD_PARTY_LIABILITY";
    }
}
