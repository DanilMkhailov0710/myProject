package org.travel.core.validators;

import org.travel.dto.Request.TravelCalculatePremiumRequest;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class ValidatorConstraintDateFromByFutureTest {
    private final Validator validator = new ValidatorConstraintDateFromByFuture();

    @Test
    public void testNull(){
        var request = new TravelCalculatePremiumRequest();
        var result = validator.validate(request);
        assertFalse(result.isEmpty());
    }

    @Test
    public  void testDateBeforeLocalDate(){
        var request = new TravelCalculatePremiumRequest();
        LocalDate now = LocalDate.now();
        request.setAgreementDateFrom(LocalDate.of(now.getYear()-1, now.getMonthValue(), now.getDayOfMonth()));
        var result = validator.validate(request);
        assertFalse(result.isEmpty());
    }

    @Test
    public  void testDateCorrectlyEqualsOne(){
        var request = new TravelCalculatePremiumRequest();
        request.setAgreementDateFrom(LocalDate.now());
        var result = validator.validate(request);
        assertTrue(result.isEmpty());
    }

    @Test
    public  void testDateCorrectlyTwo(){
        var request = new TravelCalculatePremiumRequest();
        LocalDate now = LocalDate.now();
        request.setAgreementDateFrom(LocalDate.of(now.getYear()+1, now.getMonthValue(), now.getDayOfMonth()));
        var result = validator.validate(request);
        assertTrue(result.isEmpty());
    }
}