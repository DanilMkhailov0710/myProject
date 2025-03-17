package org.travel.insurance.core.validations.agreement;

import org.mockito.Mock;
import org.mockito.InjectMocks;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.when;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.ArgumentMatchers.anyList;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.travel.insurance.core.api.dto.AgreementDTO;
import org.travel.insurance.core.api.dto.ValidationErrorDTO;
import org.travel.insurance.core.domain.ClassifierValue;
import org.travel.insurance.core.repositories.ClassifierValueRepository;
import org.travel.insurance.core.validations.ValidationErrorFactory;

import java.util.Optional;

@ExtendWith(MockitoExtension.class)
class ValidatorCheckCorrectlySelectedCountryTest {
    
    @Mock private ClassifierValueRepository repository;
    @Mock private ValidationErrorFactory errorFactory;
    @InjectMocks private ValidatorAgreementCheckCorrectlySelectedCountry validator;

    @Test
    public void checkByNullCountry(){
        var request = new AgreementDTO();
        assertTrue(validator.validate(request).isEmpty());
    }

    @Test
    public void first(){
        var request = new AgreementDTO();
        request.setCountry("Grara");
        settingsThrowError();
        assertTrue(validator.validate(request).isPresent());
    }

    @Test
    public void second(){
        var request = new AgreementDTO();
        request.setCountry("JAPAN");
        when(repository.findByClassifierTitleAndIc("COUNTRY", "JAPAN")).thenReturn(Optional.of(new ClassifierValue()));
        assertTrue(validator.validate(request).isEmpty());
    }

    private void settingsThrowError(){
        var validationError = new ValidationErrorDTO("ERROR_CODE", "Unknown error");
        when(errorFactory.buildValidationError(eq("ERROR_CODE_11"), anyList()))
                .thenReturn(validationError);
    }
    
}