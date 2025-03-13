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
class ValidatorConstraintDateToByFutureTest {

    @Mock private ValidationErrorFactory builderErrors;
    @InjectMocks private ValidatorConstraintDateToByFuture validator;

    private void repeatIniMock(){
        when(builderErrors.buildValidationError("ERROR_CODE_6"))
                .thenReturn(new ValidationError("ERROR_CODE_6", "description6"));
    }

    @Test
    public void testNull(){
        var request = new TravelCalculatePremiumRequestV1();
        repeatIniMock();
        var result = validator.validate(request);
        assertFalse(result.isEmpty());
    }

    @Test
    public  void testDateBeforeLocalDate(){
        var request = new TravelCalculatePremiumRequestV1();
        LocalDate now = LocalDate.now();
        request.setAgreementDateTo(LocalDate.of(now.getYear()-1, now.getMonthValue(), now.getDayOfMonth()));
        repeatIniMock();
        var result = validator.validate(request);
        assertFalse(result.isEmpty());
    }

    @Test
    public  void testDateCorrectlyEqualsOne(){
        var request = new TravelCalculatePremiumRequestV1();
        request.setAgreementDateTo(LocalDate.now());
        var result = validator.validate(request);
        assertTrue(result.isEmpty());
    }

    @Test
    public  void testDateCorrectlyTwo(){
        var request = new TravelCalculatePremiumRequestV1();
        LocalDate now = LocalDate.now();
        request.setAgreementDateTo(LocalDate.of(now.getYear()+1, now.getMonthValue(), now.getDayOfMonth()));
        var result = validator.validate(request);
        assertTrue(result.isEmpty());
    }
}