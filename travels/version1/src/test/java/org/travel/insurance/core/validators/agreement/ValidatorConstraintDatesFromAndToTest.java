package org.travel.insurance.core.validators.agreement;

import org.travel.insurance.core.util.ValidationErrorFactory;
import org.travel.insurance.dto.v1.TravelCalculatePremiumRequestV1;
import org.travel.insurance.dto.ValidationError;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ValidatorConstraintDatesFromAndToTest {

    @Mock private ValidationErrorFactory builderErrors;
    @InjectMocks private ValidatorConstraintDatesFromAndTo validator;

    private void repeatIniMock(){
        when(builderErrors.buildValidationError("ERROR_CODE_7"))
                .thenReturn(new ValidationError("ERROR_CODE_7", "description7"));
    }

    @Test
    public void testEmpty(){
        var request = new TravelCalculatePremiumRequestV1();
        repeatIniMock();
        var errors = validator.validate(request);
        assertFalse(errors.isEmpty());
    }

    @Test
    public void testIncorrectlyOne(){
        var request = new TravelCalculatePremiumRequestV1();
        request.setAgreementDateFrom(LocalDate.of(2025, 1, 1));
        repeatIniMock();
        var errors = validator.validate(request);
        assertFalse(errors.isEmpty());
    }


    @Test
    public void testIncorrectlyTwo(){
        var request = new TravelCalculatePremiumRequestV1();
        request.setAgreementDateTo(LocalDate.now());
        request.setPersonFirstName("Vasia");
        request.setPersonLastName("Pupkin");
        repeatIniMock();
        var errors = validator.validate(request);
        assertFalse(errors.isEmpty());
    }

    @Test
    public void testIncorrectlyThree(){
        var request = new TravelCalculatePremiumRequestV1();
        request.setAgreementDateTo(LocalDate.of(2025, 1, 1));
        request.setAgreementDateFrom(LocalDate.of(2025, 2, 9));
        request.setPersonFirstName("Vasia");
        request.setPersonLastName("Pupkin");
        repeatIniMock();
        var errors = validator.validate(request);
        assertFalse(errors.isEmpty());
    }

    @Test
    public void testCorrectly(){
        var request = new TravelCalculatePremiumRequestV1();
        request.setAgreementDateFrom(LocalDate.of(2025, 2, 9));
        request.setAgreementDateTo(LocalDate.of(2025, 2, 9));
        var errors = validator.validate(request);
        assertTrue(errors.isEmpty());
    }


}