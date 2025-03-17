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

import java.util.List;

@ExtendWith(MockitoExtension.class)
class ValidatorCheckCountryNotNullBySelectedMedicalRiskTest {

    @Mock private ValidationErrorFactory builderErrors;
    @InjectMocks private ValidatorAgreementCheckCountryNotNullBySelectedMedicalRisk validator;

    @Test
    public void checkByNullRisksAndCountry(){
        var request = new AgreementDTO();
        settingsThrowError();
        assertTrue(validator.validate(request).isPresent());
    }

    @Test
    public void checkByNulCountryWithUnnecessaryRisks(){
        var request = new AgreementDTO();
        request.setSelectedRisks(List.of("TRAVEL_CANCELLATION"));
        settingsThrowError();
        assertTrue(validator.validate(request).isPresent());
    }

    @Test
    public void checkInvokeError(){
        var request = new AgreementDTO();
        request.setSelectedRisks(List.of("TRAVEL_MEDICAL"));
        settingsThrowError();
        assertTrue(validator.validate(request).isPresent());
    }

    @Test
    public void fullCorrectly(){
        var request = new AgreementDTO();
        request.setSelectedRisks(List.of("TRAVEL_MEDICAL"));
        request.setCountry("JAPAN");
        assertTrue(validator.validate(request).isEmpty());
    }

    private void settingsThrowError(){
        when(builderErrors.buildValidationError("ERROR_CODE_10"))
                .thenReturn(new ValidationErrorDTO("ERROR_CODE_10", "description10"));
    }

}