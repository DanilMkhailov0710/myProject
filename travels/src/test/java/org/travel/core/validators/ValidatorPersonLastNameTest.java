package org.travel.core.validators;

import org.travel.dto.Request.TravelCalculatePremiumRequest;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;


class ValidatorPersonLastNameTest {
    private final ValidatorPersonLastName validator = new ValidatorPersonLastName();

    @Test
    public void testValidatorNull(){
        var request = new TravelCalculatePremiumRequest();
        var errors =  validator.validate(request);
        assertFalse(errors.isEmpty());
    }

    @Test
    public void testValidatorEmpty(){
        var request = new TravelCalculatePremiumRequest();
        request.setPersonLastName("");
        request.setAgreementDateTo(LocalDate.now());
        request.setPersonFirstName("Vasia");
        request.setAgreementDateTo(LocalDate.now());
        var errors =  validator.validate(request);
        assertFalse(errors.isEmpty());
    }

    @Test
    public void testValidatorCorrectlyTwo(){
        var request = new TravelCalculatePremiumRequest();
        request.setPersonLastName("Pupkin");
        var errors =  validator.validate(request);
        assertTrue(errors.isEmpty());
    }

    @Test
    public void testValidatorCorrectlyOne(){
        var request = new TravelCalculatePremiumRequest();
        request.setPersonFirstName("Vasia");
        var errors =  validator.validate(request);
        assertFalse(errors.isEmpty());
    }

}