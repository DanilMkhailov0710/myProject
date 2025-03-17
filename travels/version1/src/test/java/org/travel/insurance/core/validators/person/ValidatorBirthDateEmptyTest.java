package org.travel.insurance.core.validators.person;

import org.mockito.Mock;
import org.mockito.InjectMocks;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import org.springframework.test.util.ReflectionTestUtils;

import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.travel.insurance.core.util.ValidationErrorFactory;
import org.travel.insurance.dto.v1.TravelCalculatePremiumRequestV1;
import org.travel.insurance.dto.ValidationError;

import java.time.LocalDate;

@ExtendWith(MockitoExtension.class)
class ValidatorBirthDateEmptyTest {

    @Mock private ValidationErrorFactory builderErrors;
    @InjectMocks private ValidatorBirthDateEmpty validator;

    @Test
    public void testNull(){
        TravelCalculatePremiumRequestV1 request = new TravelCalculatePremiumRequestV1();
        settingsThrowError();
        setEnabledTrue();
        var errors =  validator.validate(request);
        assertTrue(errors.isPresent());
    }

    @Test
    public void testNullByFalse(){
        TravelCalculatePremiumRequestV1 request = new TravelCalculatePremiumRequestV1();
        setEnabledFalse();
        var errors =  validator.validate(request);
        assertTrue(errors.isEmpty());
    }

    @Test
    public void testCorrectly(){
        TravelCalculatePremiumRequestV1 request = new TravelCalculatePremiumRequestV1();
        request.setPersonBirthDate(LocalDate.now());
        setEnabledTrue();
        var errors =  validator.validate(request);
        assertTrue(errors.isEmpty());
    }

    private void setEnabledTrue(){
        ReflectionTestUtils.setField(validator, "medicalRiskAgeCoefficientEnabled", true);
    }

    private void setEnabledFalse(){
        ReflectionTestUtils.setField(validator, "medicalRiskAgeCoefficientEnabled", false);
    }

    private void settingsThrowError(){
        when(builderErrors.buildValidationError("ERROR_CODE_12"))
                .thenReturn(new ValidationError("ERROR_CODE_12", "description12"));
    }

}