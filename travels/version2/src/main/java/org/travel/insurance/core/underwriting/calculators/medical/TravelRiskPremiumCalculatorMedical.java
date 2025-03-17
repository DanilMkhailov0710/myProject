package org.travel.insurance.core.underwriting.calculators.medical;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import org.travel.insurance.core.api.dto.PersonDTO;
import org.travel.insurance.core.api.dto.AgreementDTO;

import org.travel.insurance.core.underwriting.TravelRiskPremiumCalculator;

import java.math.BigDecimal;

@Component
@RequiredArgsConstructor
class TravelRiskPremiumCalculatorMedical implements TravelRiskPremiumCalculator {

    private final CalculatorDaysBetween calculatorDays;
    private final CalculatorDefaultDayPremium calculatorDefaultDay;
    private final CalculatorAgeCoefficient calculatorAge;
    private final CalculatorInsuranceLimitCoefficient calculatorInsurance;

    @Override
    public BigDecimal calculatePremium(AgreementDTO agreement, PersonDTO person ) {
        var countryDefaultDayPremium = calculatorDefaultDay.calculate(agreement);
        var dayCount = calculatorDays.calculate(agreement);
        var ageCoefficient = calculatorAge.calculate(person);
        var insuranceLim = calculatorInsurance.calculate(person);

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