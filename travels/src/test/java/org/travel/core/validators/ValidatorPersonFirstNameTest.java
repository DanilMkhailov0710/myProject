package org.travel.core.validators;

import org.travel.dto.Request.TravelCalculatePremiumRequest;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;


class ValidatorPersonFirstNameTest {
    private final ValidatorPersonFirstName validator = new ValidatorPersonFirstName();

    @Test
    public void testValidatorNull(){
        var request = new TravelCalculatePremiumRequest();
        var errors =  validator.validate(request);
        assertFalse(errors.isEmpty());
    }

    @Test
    public void testValidatorEmpty(){
        var request = new TravelCalculatePremiumRequest();
        request.setPersonFirstName("");
        var errors =  validator.validate(request);
        assertFalse(errors.isEmpty());
    }

    @Test
    public void testValidatorCorrectly(){
        var request = new TravelCalculatePremiumRequest();
        request.setPersonFirstName("Danil");
        var errors =  validator.validate(request);
        assertTrue(errors.isEmpty());
    }

    @Test
    public void testValidatorIncorrectly(){
        var request = new TravelCalculatePremiumRequest();
        request.setPersonLastName("Danil");
        request.setAgreementDateFrom(LocalDate.now());
        request.setAgreementDateTo(LocalDate.now());
        var errors =  validator.validate(request);
        assertFalse(errors.isEmpty());
    }
}