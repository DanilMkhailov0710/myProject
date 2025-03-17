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
class ValidatorConstraintDateFromByFutureTest {

    @Mock private ValidationErrorFactory builderErrors;
    @InjectMocks private ValidatorAgreementConstraintDateFromByFuture validator;

    @Test
    public void testNull(){
        var request = new AgreementDTO();
        settingsThrowError();
        var result = validator.validate(request);
        assertTrue(result.isPresent());
    }

    @Test
    public  void testDateBeforeLocalDate(){
        var request = new AgreementDTO();
        LocalDate now = LocalDate.now();
        request.setAgreementDateFrom(LocalDate.of(now.getYear()-1, now.getMonthValue(), now.getDayOfMonth()));
        settingsThrowError();
        var result = validator.validate(request);
        assertTrue(result.isPresent());
    }

    @Test
    public  void testDateCorrectlyEqualsOne(){
        var request = new AgreementDTO();
        request.setAgreementDateFrom(LocalDate.now());
        var result = validator.validate(request);
        assertTrue(result.isEmpty());
    }

    @Test
    public  void testDateCorrectlyTwo(){
        var request = new AgreementDTO();
        LocalDate now = LocalDate.now();
        request.setAgreementDateFrom(LocalDate.of(now.getYear()+1, now.getMonthValue(), now.getDayOfMonth()));
        var result = validator.validate(request);
        assertTrue(result.isEmpty());
    }

    private void settingsThrowError(){
        when(builderErrors.buildValidationError("ERROR_CODE_5"))
                .thenReturn(new ValidationErrorDTO("ERROR_CODE_5", "description5"));
    }

}