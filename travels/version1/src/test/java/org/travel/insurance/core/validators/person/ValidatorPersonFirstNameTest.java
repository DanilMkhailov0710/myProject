package org.travel.insurance.core.validators.person;

import org.travel.insurance.core.util.ValidationErrorFactory;
import org.travel.insurance.dto.v1.TravelCalculatePremiumRequestV1;
import org.travel.insurance.dto.ValidationError;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ValidatorPersonFirstNameTest {

    @Mock private ValidationErrorFactory builderErrors;
    @InjectMocks private ValidatorPersonFirstName validator;

    private void repeatIniMock(){
        when(builderErrors.buildValidationError("ERROR_CODE_1"))
                .thenReturn(new ValidationError("ERROR_CODE_1", "description1"));
    }

    @Test
    public void testValidatorNull(){
        var request = new TravelCalculatePremiumRequestV1();
        repeatIniMock();
        var errors =  validator.validate(request);
        assertFalse(errors.isEmpty());
    }

    @Test
    public void testValidatorEmpty(){
        var request = new TravelCalculatePremiumRequestV1();
        request.setPersonFirstName("");
        repeatIniMock();
        var errors =  validator.validate(request);
        assertFalse(errors.isEmpty());
    }

    @Test
    public void testValidatorIncorrectly(){
        var request = new TravelCalculatePremiumRequestV1();
        request.setPersonLastName("Danil");
        request.setAgreementDateFrom(LocalDate.now());
        request.setAgreementDateTo(LocalDate.now());
        repeatIniMock();
        var errors =  validator.validate(request);
        assertFalse(errors.isEmpty());
    }

    @Test
    public void testValidatorCorrectly(){
        var request = new TravelCalculatePremiumRequestV1();
        request.setPersonFirstName("Danil");
        var errors =  validator.validate(request);
        assertTrue(errors.isEmpty());
    }
}