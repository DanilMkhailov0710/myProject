package org.travel.insurance.core.validators.agreement;

import org.mockito.Mock;
import org.mockito.InjectMocks;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import org.springframework.test.util.ReflectionTestUtils;

import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.travel.insurance.core.repositories.MedicalRiskLimitLevelRepository;
import org.travel.insurance.core.util.Placeholder;
import org.travel.insurance.core.util.ValidationErrorFactory;
import org.travel.insurance.dto.v1.TravelCalculatePremiumRequestV1;
import org.travel.insurance.dto.ValidationError;

import java.util.List;

@ExtendWith(MockitoExtension.class)
class ValidatorMedicalRiskLimitLevelCorrectlyValueTest {

    @Mock private ValidationErrorFactory builderErrors;
    @Mock private MedicalRiskLimitLevelRepository repository;
    @InjectMocks private ValidatorMedicalRiskLimitLevel validator;

    @Test
    public void failByNull(){
        var request = new TravelCalculatePremiumRequestV1();
        request.setMedicalRiskLimitLevel("LEVEL_100");
        //when(repository.findByMedicalRiskLimitLevelIc("LEVEL_100"))
                //.thenReturn(Optional.of(new MedicalRiskLimitLevel()));
        setEnabledTrue();
        //setBuilderErrors();
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
        var placeholder = new Placeholder("NOT_EXISTING_MEDICAL_RISC_LIMIT_LEVEL_IC", "Value");
        var error = new ValidationError("ERROR_CODE_15", "FAIL");
        when(builderErrors.buildValidationError("ERROR_CODE_15", List.of(placeholder))).thenReturn(error);
    }

}