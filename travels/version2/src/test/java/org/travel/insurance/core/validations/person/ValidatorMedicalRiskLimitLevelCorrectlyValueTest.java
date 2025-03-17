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

import org.travel.insurance.core.util.Placeholder;
import org.travel.insurance.core.validations.ValidationErrorFactory;
import org.travel.insurance.core.repositories.MedicalRiskLimitLevelRepository;

import java.util.List;

@ExtendWith(MockitoExtension.class)
class ValidatorMedicalRiskLimitLevelCorrectlyValueTest {

    @Mock private ValidationErrorFactory builderErrors;

    @Mock private MedicalRiskLimitLevelRepository repository;

    @InjectMocks private ValidatorAgreementMedicalRiskLimitLevel validator;

    @Test
    public void failByNull(){
        var request = new PersonDTO();
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
        var error = new ValidationErrorDTO("ERROR_CODE_15", "FAIL");
        when(builderErrors.buildValidationError("ERROR_CODE_15", List.of(placeholder))).thenReturn(error);
    }

}