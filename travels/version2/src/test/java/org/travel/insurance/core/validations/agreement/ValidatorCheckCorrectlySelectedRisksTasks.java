package org.travel.insurance.core.validations.agreement;

import org.mockito.Mock;
import org.mockito.InjectMocks;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.ArgumentMatchers.anyList;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.travel.insurance.core.api.dto.AgreementDTO;
import org.travel.insurance.core.api.dto.ValidationErrorDTO;

import org.travel.insurance.core.domain.ClassifierValue;
import org.travel.insurance.core.validations.ValidationErrorFactory;

import org.travel.insurance.core.repositories.ClassifierValueRepository;

import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
class ValidatorCheckCorrectlySelectedRisksTasks {

    @Mock private ClassifierValueRepository repository;
    @Mock private ValidationErrorFactory errorFactory;
    @InjectMocks private ValidatorAgreementCheckCorrectlySelectedRisks validator;

    @Test
    public void activateOneError(){
        var classifierValue = new ClassifierValue();
        var request = repeatBlock(Optional.of(classifierValue), Optional.empty());
        settingsThrowError();
        assertEquals(1, validator.validate(request).size());
    }

    @Test
    public void activateEitherErrors(){
        var request = repeatBlock(Optional.empty(), Optional.empty());
        settingsThrowError();
        assertEquals(2, validator.validate(request).size());
    }

    @Test
    public void correctly(){
        var classifierValue = new ClassifierValue();
        var request = repeatBlock(Optional.of(classifierValue), Optional.of(classifierValue));
        assertTrue(validator.validate(request).isEmpty());
    }

    private AgreementDTO repeatBlock(Optional<ClassifierValue> value1, Optional<ClassifierValue> value2){
        var request = mock(AgreementDTO.class);
        when(request.getSelectedRisks()).thenReturn(List.of("RISK1", "RISK2"));
        when(repository.findByClassifierTitleAndIc("RISK_TYPE", "RISK1"))
                .thenReturn(value1);
        when(repository.findByClassifierTitleAndIc("RISK_TYPE", "RISK2"))
                .thenReturn(value2);

        return request;
    }

    private void settingsThrowError(){
        var validationError = new ValidationErrorDTO("ERROR_CODE", "Unknown error");
        when(errorFactory.buildValidationError(eq("ERROR_CODE_9"), anyList()))
                .thenReturn(validationError);
    }

}