package org.travel.insurance.core.underwriting.calculators.medical;

import org.mockito.Mock;
import org.mockito.InjectMocks;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import org.travel.insurance.core.api.dto.AgreementDTO;

import org.travel.insurance.core.util.DateTimeService;

import static org.mockito.Mockito.when;
import static org.mockito.ArgumentMatchers.any;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;
import java.math.BigDecimal;

@ExtendWith(MockitoExtension.class)
class CalculatorDaysBetweenTest {

    @Mock private DateTimeService dateTimeService;
    @InjectMocks private CalculatorDaysBetween calculator;

    @Test
    public void test1(){
        when(dateTimeService.computeAmountOfDays(any(), any())).thenReturn(2L);
        var request = new AgreementDTO();
        request.setAgreementDateFrom(LocalDate.now());
        request.setAgreementDateTo(LocalDate.now());
        assertEquals(BigDecimal.TWO, calculator.calculate(request));
    }

}