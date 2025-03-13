package org.travel.insurance.core.underwriting;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;

import org.travel.insurance.dto.v1.TravelCalculatePremiumRequestV1;
import org.travel.insurance.dto.Risk;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
@RequiredArgsConstructor(access = AccessLevel.PACKAGE)
class TravelCalculatorPremiumPriceImp implements TravelPremiumUnderwriting{

    private final SelectedRisksPremiumCalculator calculatorSelectedRisks;

    public TravelStoragePremiumSelectedRisks computePrice(TravelCalculatePremiumRequestV1 request){

        var risks = calculatorSelectedRisks.getSelectedRisks(request);

        BigDecimal totalPrice = risks.stream()
                .map(Risk::getPremium)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        return new TravelStoragePremiumSelectedRisks(risks, totalPrice);
    }
}
