package org.travel.insurance.core.validators.person;

import org.mockito.Mock;
import org.mockito.InjectMocks;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.travel.insurance.core.util.ValidationErrorFactory;
import org.travel.insurance.dto.v1.TravelCalculatePremiumRequestV1;
import org.travel.insurance.dto.ValidationError;

import java.time.LocalDate;

@ExtendWith(MockitoExtension.class)
class ValidatorPersonLastNameTest {

    @Mock private ValidationErrorFactory builderErrors;
    @InjectMocks private ValidatorPersonLastName validator;

    @Test
    public void testValidatorNull(){
        var request = new TravelCalculatePremiumRequestV1();
        settingsThrowError();
        var errors =  validator.validate(request);
        assertTrue(errors.isPresent());
    }

    @Test
    public void testValidatorEmpty(){
        var request = new TravelCalculatePremiumRequestV1();
        request.setPersonLastName("");
        request.setAgreementDateTo(LocalDate.now());
        request.setPersonFirstName("Vasia");
        request.setAgreementDateTo(LocalDate.now());
        settingsThrowError();
        var errors =  validator.validate(request);
        assertTrue(errors.isPresent());
    }

    @Test
    public void testValidatorIncorrectly(){
        var request = new TravelCalculatePremiumRequestV1();
        request.setPersonFirstName("Vasia");
        settingsThrowError();
        var errors =  validator.validate(request);
        assertTrue(errors.isPresent());
    }

    @Test
    public void testValidatorCorrectlyTwo(){
        var request = new TravelCalculatePremiumRequestV1();
        request.setPersonLastName("Pupkin");
        var errors =  validator.validate(request);
        assertTrue(errors.isEmpty());
    }

    private void settingsThrowError(){
        when(builderErrors.buildValidationError("ERROR_CODE_2"))
                .thenReturn(new ValidationError("ERROR_CODE_2", "description2"));
    }

}