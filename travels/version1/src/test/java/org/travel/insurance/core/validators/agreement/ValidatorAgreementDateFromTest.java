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
class ValidatorAgreementDateFromTest {

    @Mock private ValidationErrorFactory builderErrors;
    @InjectMocks private ValidatorAgreementDateFrom validator;

    private void repeatIniMock(){
        when(builderErrors.buildValidationError("ERROR_CODE_3"))
                .thenReturn(new ValidationError("ERROR_CODE_3", "description3"));
    }

    @Test
    public void testNull(){
        TravelCalculatePremiumRequestV1 request = new TravelCalculatePremiumRequestV1();
        repeatIniMock();
        var errors =  validator.validate(request);
        assertFalse(errors.isEmpty());
    }

    @Test
    public void testIncorrectly(){
        TravelCalculatePremiumRequestV1 request = new TravelCalculatePremiumRequestV1();
        request.setAgreementDateTo(LocalDate.of(2025, 2, 9));
        request.setPersonFirstName("Vasia");
        request.setPersonLastName("Pupkin");
        repeatIniMock();
        var errors =  validator.validate(request);
        assertFalse(errors.isEmpty());
    }

    @Test
    public void testCorrectly(){
        TravelCalculatePremiumRequestV1 request = new TravelCalculatePremiumRequestV1();
        request.setAgreementDateFrom(LocalDate.of(2025, 2, 9));
        var errors =  validator.validate(request);
        assertTrue(errors.isEmpty());
    }
}