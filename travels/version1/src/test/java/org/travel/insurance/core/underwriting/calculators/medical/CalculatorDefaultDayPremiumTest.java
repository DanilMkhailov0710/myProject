package org.travel.insurance.core.underwriting.calculators.medical;

import org.travel.insurance.core.domain.CountryDefaultDayRate;
import org.travel.insurance.core.repositories.CountryDefaultDayRateRepository;
import org.travel.insurance.dto.v1.TravelCalculatePremiumRequestV1;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CalculatorDefaultDayPremiumTest {

    @Mock private CountryDefaultDayRateRepository repository;
    @InjectMocks private CalculatorDefaultDayPremium calculator;

    @Test
    public void test1(){
        var request = new TravelCalculatePremiumRequestV1();
        request.setCountry("COUNTRY");
        var domenchik = new CountryDefaultDayRate();
        domenchik.setDefaultDayRate(BigDecimal.TWO);
        when(repository.findByCountryIc("COUNTRY")).thenReturn(Optional.of(domenchik));
        assertEquals(new BigDecimal(2), calculator.calculate(request));

    }

}