package org.travel.insurance.core.validations.person;

import org.mockito.Mock;
import org.mockito.InjectMocks;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.springframework.test.util.ReflectionTestUtils;

import org.travel.insurance.core.api.dto.PersonDTO;
import org.travel.insurance.core.api.dto.ValidationErrorDTO;

import org.travel.insurance.core.validations.ValidationErrorFactory;

import java.time.LocalDate;

@ExtendWith(MockitoExtension.class)
class ValidatorBirthDateEmptyTest {

    @Mock private ValidationErrorFactory builderErrors;
    @InjectMocks private ValidatorBirthDateEmpty validator;

    @Test
    public void testNull(){
        var request = new PersonDTO();
        settingsThrowError();
        setEnabledTrue();
        var errors =  validator.validate(request);
        assertTrue(errors.isPresent());
    }

    @Test
    public void testNullByFalse(){
        var request = new PersonDTO();
        setEnabledFalse();
        var errors =  validator.validate(request);
        assertTrue(errors.isEmpty());
    }

    @Test
    public void testCorrectly(){
        var request = new PersonDTO();
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
                .thenReturn(new ValidationErrorDTO("ERROR_CODE_12", "description12"));
    }

}