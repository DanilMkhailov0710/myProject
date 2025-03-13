package org.travel.insurance.core.underwriting.calculators.medical;

import org.travel.insurance.core.util.DateTimeService;
import org.travel.insurance.dto.v1.TravelCalculatePremiumRequestV1;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CalculatorDaysBetweenTest {
    @Mock
    private DateTimeService dateTimeService;
    @InjectMocks
    private CalculatorDaysBetween calculator;

    @Test
    public void test1(){
        when(dateTimeService.computeAmountOfDays(any(), any())).thenReturn(2L);
        var request = new TravelCalculatePremiumRequestV1();
        request.setAgreementDateFrom(LocalDate.now());
        request.setAgreementDateTo(LocalDate.now());
        assertEquals(BigDecimal.TWO, calculator.calculate(request));
    }
}