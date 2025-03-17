package org.travel.insurance.core.underwriting.calculators.medical;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import org.travel.insurance.core.api.dto.AgreementDTO;
import org.travel.insurance.core.util.DateTimeService;

import java.math.BigDecimal;

@Component
@RequiredArgsConstructor
class CalculatorDaysBetween {

    private final DateTimeService dateTimeService;

    public BigDecimal calculate(AgreementDTO agreement){

        return new BigDecimal(
                dateTimeService.computeAmountOfDays(
                        agreement.getAgreementDateFrom(),
                        agreement.getAgreementDateTo()
                ));
    }

}
