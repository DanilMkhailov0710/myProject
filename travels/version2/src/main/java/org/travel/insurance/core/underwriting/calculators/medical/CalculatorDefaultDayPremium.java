package org.travel.insurance.core.underwriting.calculators.medical;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import org.travel.insurance.core.api.dto.AgreementDTO;

import org.travel.insurance.core.domain.CountryDefaultDayRate;
import org.travel.insurance.core.repositories.CountryDefaultDayRateRepository;

import java.math.BigDecimal;

@Component
@RequiredArgsConstructor
class CalculatorDefaultDayPremium {

    private final CountryDefaultDayRateRepository repositoryDay;

    public BigDecimal calculate(AgreementDTO agreement){
        String countryIc = agreement.getCountry();

        var countryFromQuery = repositoryDay.findByCountryIc(countryIc);

        return countryFromQuery
                .map(CountryDefaultDayRate::getDefaultDayRate)
                .orElse(BigDecimal.ZERO);
    }

}
