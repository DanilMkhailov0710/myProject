package org.travel.insurance.core.validators.agreement;

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
class ValidatorAgreementDateFromTest {

    @Mock private ValidationErrorFactory builderErrors;
    @InjectMocks private ValidatorAgreementDateFrom validator;

    @Test
    public void testNull(){
        TravelCalculatePremiumRequestV1 request = new TravelCalculatePremiumRequestV1();
        settingsThrowError();
        var errors =  validator.validate(request);
        assertTrue(errors.isPresent());
    }

    @Test
    public void testIncorrectly(){
        TravelCalculatePremiumRequestV1 request = new TravelCalculatePremiumRequestV1();
        request.setAgreementDateTo(LocalDate.of(2025, 2, 9));
        request.setPersonFirstName("Vasia");
        request.setPersonLastName("Pupkin");
        settingsThrowError();
        var errors =  validator.validate(request);
        assertTrue(errors.isPresent());
    }

    @Test
    public void testCorrectly(){
        TravelCalculatePremiumRequestV1 request = new TravelCalculatePremiumRequestV1();
        request.setAgreementDateFrom(LocalDate.of(2025, 2, 9));
        var errors =  validator.validate(request);
        assertTrue(errors.isEmpty());
    }

    private void settingsThrowError(){
        when(builderErrors.buildValidationError("ERROR_CODE_3"))
                .thenReturn(new ValidationError("ERROR_CODE_3", "description3"));
    }

}