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
import java.util.ArrayList;

@ExtendWith(MockitoExtension.class)
class ValidatorSelectedEmptyRisksTest {

    @Mock private ValidationErrorFactory builderErrors;
    @InjectMocks private ValidatorAgreementSelectedEmptyRisks validator;

    @Test
    public void testValidatorNull(){
        var request = new AgreementDTO();
        settingsThrowError();
        var errors =  validator.validate(request);
        assertTrue(errors.isPresent());
    }

    @Test
    public void testValidatorEmpty(){
        var request = new AgreementDTO();
        request.setAgreementDateTo(LocalDate.now());
        request.setSelectedRisks(new ArrayList<>());
        settingsThrowError();
        var errors =  validator.validate(request);
        assertTrue(errors.isPresent());
    }

    @Test
    public void testValidatorCorrectlyOne(){
        var request = new AgreementDTO();
        var risks = new ArrayList<String>();
        risks.add("TRAVEL_MEDICAL");
        request.setSelectedRisks(risks);
        var errors =  validator.validate(request);
        assertTrue(errors.isEmpty());
    }

    private void settingsThrowError(){
        when(builderErrors.buildValidationError("ERROR_CODE_8"))
                .thenReturn(new ValidationErrorDTO("ERROR_CODE_8", "description8"));
    }

}