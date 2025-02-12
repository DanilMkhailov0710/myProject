package org.travel.core;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.travel.dto.Request.TravelCalculatePremiumRequest;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
@RequiredArgsConstructor(access = AccessLevel.PACKAGE)
class TravelCalculatorPremiumPrice {
    private final DateTimeService dateTimeService;
    public BigDecimal computePrice(TravelCalculatePremiumRequest request){
        var daysBetween = dateTimeService.computeAmountOfDays(
                request.getAgreementDateFrom(),
                request.getAgreementDateTo()
        );

        return new BigDecimal(daysBetween);
    }
}
