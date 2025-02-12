package org.travel.core;

import org.travel.core.validators.TravelCalculatePremiumRequestValidator;
import org.travel.dto.Request.TravelCalculatePremiumRequest;
import org.travel.dto.Response.TravelCalculatePremiumResponse;
import org.travel.dto.ValidationError;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class TravelCalculatePremiumServiceImplTest {
    @Mock private TravelCalculatorPremiumPrice function;
    @Mock private TravelCalculatePremiumRequestValidator validator;

    @InjectMocks
    private TravelCalculatePremiumServiceImpl service;

    @Test
    public void testForCheckCorrectlyBroadcastFistName(){
        TravelCalculatePremiumRequest request = requestInit();


        TravelCalculatePremiumResponse response = service.calculatePremium(request);
        assertEquals(request.getPersonFirstName(), response.getPersonFirstName());
    }

    @Test
    public void testForCheckCorrectlyBroadcastLastName(){
        TravelCalculatePremiumRequest request = requestInit();
        when(validator.validate(request)).thenReturn(List.of());

        TravelCalculatePremiumResponse response = service.calculatePremium(request);
        assertEquals(request.getPersonLastName(), response.getPersonLastName());
    }

    @Test
    public void testForCheckCorrectlyBroadcastAgreementDateFrom(){
        TravelCalculatePremiumRequest request = requestInit();
        when(validator.validate(request)).thenReturn(List.of());

        TravelCalculatePremiumResponse response = service.calculatePremium(request);
        assertEquals(request.getAgreementDateFrom(), response.getAgreementDateFrom());
    }

    @Test
    public void testForCheckCorrectlyBroadcastAgreementDateTo(){
        TravelCalculatePremiumRequest request = requestInit();
        when(validator.validate(request)).thenReturn(List.of());

        TravelCalculatePremiumResponse response = service.calculatePremium(request);
        assertEquals(request.getAgreementDateTo(), response.getAgreementDateTo());
    }

    @Test
    public void trySwitchErrorsForFirstName(){
        TravelCalculatePremiumRequest request = requestInit();
        when(validator.validate(request))
                .thenReturn(List.of(new ValidationError("firstName", "null or empty")));

        TravelCalculatePremiumResponse response = service.calculatePremium(request);
        assertTrue(response.hasErrors());
    }

    private TravelCalculatePremiumRequest requestInit(){
        TravelCalculatePremiumRequest request = new TravelCalculatePremiumRequest();
        request.setPersonFirstName("Vasia");
        request.setPersonLastName("Pupkin");
        request.setAgreementDateFrom(LocalDate.of(2023, 9, 12));
        request.setAgreementDateTo(LocalDate.of(2025, 2, 7));
        return request;
    }
}