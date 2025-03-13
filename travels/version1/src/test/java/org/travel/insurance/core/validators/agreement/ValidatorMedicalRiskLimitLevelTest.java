package org.travel.insurance.core.validators.agreement;

import org.travel.insurance.core.util.ValidationErrorFactory;
import org.travel.insurance.dto.v1.TravelCalculatePremiumRequestV1;
import org.travel.insurance.dto.ValidationError;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ValidatorMedicalRiskLimitLevelTest {

    @Mock private ValidationErrorFactory builderErrors;
    @InjectMocks private ValidatorMedicalRiskLimitLevel validator;

    @Test
    public void failByNull(){
        var request = new TravelCalculatePremiumRequestV1();
        setEnabledTrue();
        setBuilderErrors();
        var errors = validator.validate(request);
        assertTrue(errors.isPresent());
    }

    @Test
    public void failByEmpty(){
        var request = new TravelCalculatePremiumRequestV1();
        request.setMedicalRiskLimitLevel("");
        setEnabledTrue();
        setBuilderErrors();
        var errors = validator.validate(request);
        assertTrue(errors.isPresent());
    }

    @Test
    public void passedByNull(){
        var request = new TravelCalculatePremiumRequestV1();
        setEnabledFalse();
        var errors = validator.validate(request);
        assertTrue(errors.isEmpty());
    }

    @Test
    public void correctlyTest(){
        var request = new TravelCalculatePremiumRequestV1();
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

    private void setBuilderErrors(){
        var error = new ValidationError("ERROR_CODE_14", "FAIL");
        when(builderErrors.buildValidationError("ERROR_CODE_14")).thenReturn(error);
    }


}