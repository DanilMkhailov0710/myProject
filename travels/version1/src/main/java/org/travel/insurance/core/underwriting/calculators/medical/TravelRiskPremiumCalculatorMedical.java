package org.travel.insurance.core.underwriting.calculators.medical;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import org.travel.insurance.core.underwriting.TravelRiskPremiumCalculator;
import org.travel.insurance.dto.v1.TravelCalculatePremiumRequestV1;

import java.math.BigDecimal;

@Component
@RequiredArgsConstructor
class TravelRiskPremiumCalculatorMedical implements TravelRiskPremiumCalculator {

    private final CalculatorDaysBetween calculatorDays;
    private final CalculatorDefaultDayPremium calculatorDefaultDay;
    private final CalculatorAgeCoefficient calculatorAge;
    private final CalculatorInsuranceLimitCoefficient calculatorInsurance;

    @Override
    public BigDecimal calculatePremium(TravelCalculatePremiumRequestV1 request) {
        var countryDefaultDayPremium = calculatorDefaultDay.calculate(request);
        var dayCount = calculatorDays.calculate(request);
        var ageCoefficient = calculatorAge.calculate(request);
        var insuranceLim = calculatorInsurance.calculate(request);

        return countryDefaultDayPremium
                .multiply(dayCount)
                .multiply(ageCoefficient)
                .multiply(insuranceLim);
    }

    @Override
    public String getRiskIc() {
        return "TRAVEL_MEDICAL";
    }

}