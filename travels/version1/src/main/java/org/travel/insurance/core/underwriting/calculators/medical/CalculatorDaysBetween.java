package org.travel.insurance.core.underwriting.calculators.medical;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import org.travel.insurance.core.util.DateTimeService;
import org.travel.insurance.dto.v1.TravelCalculatePremiumRequestV1;

import java.math.BigDecimal;

@Component
@RequiredArgsConstructor
class CalculatorDaysBetween {

    private final DateTimeService dateTimeService;

    public BigDecimal calculate(TravelCalculatePremiumRequestV1 request){

        return new BigDecimal(
                dateTimeService.computeAmountOfDays(
                        request.getAgreementDateFrom(),
                        request.getAgreementDateTo()
                ));
    }

}
