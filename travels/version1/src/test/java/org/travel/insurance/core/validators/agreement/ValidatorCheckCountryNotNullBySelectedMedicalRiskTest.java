package org.travel.insurance.core.validators.agreement;

import org.travel.insurance.core.util.ValidationErrorFactory;
import org.travel.insurance.dto.v1.TravelCalculatePremiumRequestV1;
import org.travel.insurance.dto.ValidationError;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ValidatorCheckCountryNotNullBySelectedMedicalRiskTest {

    @Mock private ValidationErrorFactory builderErrors;
    @InjectMocks private ValidatorCheckCountryNotNullBySelectedMedicalRisk validator;

    @Test
    public void checkByNullRisksAndCountry(){
        var request = new TravelCalculatePremiumRequestV1();
        settingsBuilderError();
        assertTrue(validator.validate(request).isPresent());
    }

    @Test
    public void checkByNulCountryWithUnnecessaryRisks(){
        var request = new TravelCalculatePremiumRequestV1();
        request.setSelectedRisks(List.of("TRAVEL_CANCELLATION"));
        settingsBuilderError();
        assertTrue(validator.validate(request).isPresent());
    }

    @Test
    public void checkInvokeError(){
        var request = new TravelCalculatePremiumRequestV1();
        request.setSelectedRisks(List.of("TRAVEL_MEDICAL"));
        settingsBuilderError();
        assertTrue(validator.validate(request).isPresent());
    }

    @Test
    public void fullCorrectly(){
        var request = new TravelCalculatePremiumRequestV1();
        request.setSelectedRisks(List.of("TRAVEL_MEDICAL"));
        request.setCountry("JAPAN");
        assertTrue(validator.validate(request).isEmpty());
    }

    private void settingsBuilderError(){
        when(builderErrors.buildValidationError("ERROR_CODE_10"))
                .thenReturn(new ValidationError("ERROR_CODE_10", "description10"));
    }

}