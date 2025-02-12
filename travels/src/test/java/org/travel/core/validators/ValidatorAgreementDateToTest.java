package org.travel.core.validators;

import org.travel.dto.Request.TravelCalculatePremiumRequest;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;


class ValidatorAgreementDateToTest {

    private final ValidatorAgreementDateTo validator = new ValidatorAgreementDateTo();

    @Test
    public void testNull(){
        var request = new TravelCalculatePremiumRequest();
        var errors =  validator.validate(request);
        assertFalse(errors.isEmpty());
    }

    @Test
    public void testIncorrectly(){
        var request = new TravelCalculatePremiumRequest();
        request.setAgreementDateFrom(LocalDate.of(2025, 2, 9));
        request.setPersonFirstName("Vasia");
        request.setPersonLastName("Pupkin");
        var errors =  validator.validate(request);
        assertFalse(errors.isEmpty());
    }

    @Test
    public void testCorrectly(){
        var request = new TravelCalculatePremiumRequest();
        request.setAgreementDateTo(LocalDate.of(2025, 2, 9));
        var errors =  validator.validate(request);
        assertTrue(errors.isEmpty());
    }
}