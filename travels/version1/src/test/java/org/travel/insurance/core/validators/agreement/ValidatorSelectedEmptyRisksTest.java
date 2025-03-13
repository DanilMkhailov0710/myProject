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
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ValidatorSelectedEmptyRisksTest {

    @Mock private ValidationErrorFactory builderErrors;
    @InjectMocks private ValidatorSelectedEmptyRisks validator;

    private void repeatIniMock(){
        when(builderErrors.buildValidationError("ERROR_CODE_8"))
                .thenReturn(new ValidationError("ERROR_CODE_8", "description8"));
    }

    @Test
    public void testValidatorNull(){
        var request = new TravelCalculatePremiumRequestV1();
        repeatIniMock();
        var errors =  validator.validate(request);
        assertFalse(errors.isEmpty());
    }

    @Test
    public void testValidatorEmpty(){
        var request = new TravelCalculatePremiumRequestV1();
        request.setPersonLastName("Pupkin");
        request.setAgreementDateTo(LocalDate.now());
        request.setPersonFirstName("Vasia");
        request.setAgreementDateTo(LocalDate.now());
        request.setSelectedRisks(new ArrayList<>());
        repeatIniMock();
        var errors =  validator.validate(request);
        assertFalse(errors.isEmpty());
    }

    @Test
    public void testValidatorCorrectlyOne(){
        var request = new TravelCalculatePremiumRequestV1();
        var risks = new ArrayList<String>();
        risks.add("TRAVEL_MEDICAL");
        request.setSelectedRisks(risks);
        var errors =  validator.validate(request);
        assertTrue(errors.isEmpty());
    }
}