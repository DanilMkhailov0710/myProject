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

@ExtendWith(MockitoExtension.class)
class ValidatorMedicalRiskLimitLevelTest {

    @Mock private ValidationErrorFactory builderErrors;
    @InjectMocks private ValidatorAgreementMedicalRiskLimitLevel validator;

    @Test
    public void failByNull(){
        var request = new PersonDTO();
        setEnabledTrue();
        settingsThrowError();
        var errors = validator.validate(request);
        assertTrue(errors.isPresent());
    }

    @Test
    public void failByEmpty(){
        var request = new PersonDTO();
        request.setMedicalRiskLimitLevel("");
        setEnabledTrue();
        settingsThrowError();
        var errors = validator.validate(request);
        assertTrue(errors.isPresent());
    }

    @Test
    public void passedByNull(){
        var request = new PersonDTO();
        setEnabledFalse();
        var errors = validator.validate(request);
        assertTrue(errors.isEmpty());
    }

    @Test
    public void correctlyTest(){
        var request = new PersonDTO();
        request.setMedicalRiskLimitLevel("LEVEL");
        setEnabledTrue();
        var errors = validator.validate(request);
        assertTrue(errors.isEmpty());
    }

    private void setEnabledTrue(){
        ReflectionTestUtils.setField(validator, "medicalRiskLimitLevelEnabled", true);
    }

    private void setEnabledFalse(){
        ReflectionTestUtils.setField(validator, "medicalRiskLimitLevelEnabled", false);
    }

    private void settingsThrowError(){
        var error = new ValidationErrorDTO("ERROR_CODE_14", "FAIL");
        when(builderErrors.buildValidationError("ERROR_CODE_14")).thenReturn(error);
    }

}