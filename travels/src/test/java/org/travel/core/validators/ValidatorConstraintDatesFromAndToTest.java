package org.travel.core.validators;

import org.travel.dto.Request.TravelCalculatePremiumRequest;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;


class ValidatorConstraintDatesFromAndToTest {
    private final ValidatorConstraintDatesFromAndTo validator =
            new ValidatorConstraintDatesFromAndTo();

    @Test
    public void testEmpty(){
        var request = new TravelCalculatePremiumRequest();
        var errors = validator.validate(request);
        assertFalse(errors.isEmpty());
    }

    @Test
    public void testIncorrectlyOne(){
        var request = new TravelCalculatePremiumRequest();
        request.setAgreementDateFrom(LocalDate.of(2025, 1, 1));
        var errors = validator.validate(request);
        assertFalse(errors.isEmpty());
    }


    @Test
    public void testIncorrectlyTwo(){
        var request = new TravelCalculatePremiumRequest();
        request.setAgreementDateTo(LocalDate.now());
        request.setPersonFirstName("Vasia");
        request.setPersonLastName("Pupkin");
        var errors = validator.validate(request);
        assertFalse(errors.isEmpty());
    }

    @Test
    public void testIncorrectlyThree(){
        var request = new TravelCalculatePremiumRequest();
        request.setAgreementDateTo(LocalDate.of(2025, 1, 1));
        request.setAgreementDateFrom(LocalDate.of(2025, 2, 9));
        request.setPersonFirstName("Vasia");
        request.setPersonLastName("Pupkin");
        var errors = validator.validate(request);
        assertFalse(errors.isEmpty());
    }

    @Test
    public void testCorrectly(){
        var request = new TravelCalculatePremiumRequest();
        request.setAgreementDateFrom(LocalDate.of(2025, 2, 9));
        request.setAgreementDateTo(LocalDate.of(2025, 2, 9));
        var errors = validator.validate(request);
        assertTrue(errors.isEmpty());
    }


}