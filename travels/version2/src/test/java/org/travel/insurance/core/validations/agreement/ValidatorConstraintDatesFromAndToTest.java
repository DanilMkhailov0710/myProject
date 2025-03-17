package org.travel.insurance.core.validations.agreement;

import org.mockito.Mock;
import org.mockito.InjectMocks;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.travel.insurance.core.api.dto.AgreementDTO;
import org.travel.insurance.core.api.dto.ValidationErrorDTO;
import org.travel.insurance.core.validations.ValidationErrorFactory;

import java.time.LocalDate;

@ExtendWith(MockitoExtension.class)
class ValidatorConstraintDatesFromAndToTest {

    @Mock private ValidationErrorFactory builderErrors;
    @InjectMocks private ValidatorAgreementConstraintDatesFromAndTo validator;

    @Test
    public void testEmpty(){
        var request = new AgreementDTO();
        settingsThrowError();
        var errors = validator.validate(request);
        assertTrue(errors.isPresent());
    }

    @Test
    public void testIncorrectlyOne(){
        var request = new AgreementDTO();
        request.setAgreementDateFrom(LocalDate.of(2025, 1, 1));
        settingsThrowError();
        var errors = validator.validate(request);
        assertTrue(errors.isPresent());
    }


    @Test
    public void testIncorrectlyTwo(){
        var request = new AgreementDTO();
        request.setAgreementDateTo(LocalDate.now());
        settingsThrowError();
        var errors = validator.validate(request);
        assertTrue(errors.isPresent());
    }

    @Test
    public void testIncorrectlyThree(){
        var request = new AgreementDTO();
        request.setAgreementDateTo(LocalDate.of(2025, 1, 1));
        request.setAgreementDateFrom(LocalDate.of(2025, 2, 9));
        settingsThrowError();
        var errors = validator.validate(request);
        assertTrue(errors.isPresent());
    }

    @Test
    public void testCorrectly(){
        var request = new AgreementDTO();
        request.setAgreementDateFrom(LocalDate.of(2025, 2, 9));
        request.setAgreementDateTo(LocalDate.of(2025, 2, 9));
        var errors = validator.validate(request);
        assertTrue(errors.isEmpty());
    }

    private void settingsThrowError(){
        when(builderErrors.buildValidationError("ERROR_CODE_7"))
                .thenReturn(new ValidationErrorDTO("ERROR_CODE_7", "description7"));
    }

}