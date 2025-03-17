package org.travel.insurance.core.service;

import org.mockito.Mock;
import org.mockito.InjectMocks;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.travel.insurance.core.underwriting.TravelPremiumUnderwriting;
import org.travel.insurance.core.underwriting.TravelStoragePremiumSelectedRisks;

import org.travel.insurance.core.validators.TravelCalculatePremiumRequestValidator;

import org.travel.insurance.dto.ValidationError;
import org.travel.insurance.dto.v1.TravelCalculatePremiumRequestV1;

import java.util.List;
import java.time.LocalDate;

@ExtendWith(MockitoExtension.class)
class TravelCalculatePremiumServiceImplTest {
    @Mock private TravelPremiumUnderwriting function;
    @Mock private TravelCalculatePremiumRequestValidator validator;

    @InjectMocks
    private TravelCalculatePremiumServiceImpl service;

    @Test
    public void testForCheckCorrectlyBroadcastFistName(){
        var request = requestInit();
        when(function.computePrice(request)).thenReturn(new TravelStoragePremiumSelectedRisks(null, null));
        var response = service.calculatePremium(request);
        assertEquals(request.getPersonFirstName(), response.getPersonFirstName());
    }

    @Test
    public void testForCheckCorrectlyBroadcastLastName(){
        var request = requestInit();
        when(function.computePrice(request)).thenReturn(new TravelStoragePremiumSelectedRisks(null, null));
        var response = service.calculatePremium(request);
        assertEquals(request.getPersonLastName(), response.getPersonLastName());
    }

    @Test
    public void testForCheckCorrectlyBroadcastAgreementDateFrom(){
        var request = requestInit();
        when(function.computePrice(request)).thenReturn(new TravelStoragePremiumSelectedRisks(null, null));
        var response = service.calculatePremium(request);
        assertEquals(request.getAgreementDateFrom(), response.getAgreementDateFrom());
    }

    @Test
    public void testForCheckCorrectlyBroadcastAgreementDateTo(){
        var request = requestInit();
        when(function.computePrice(request)).thenReturn(new TravelStoragePremiumSelectedRisks(null, null));
        var response = service.calculatePremium(request);
        assertEquals(request.getAgreementDateTo(), response.getAgreementDateTo());
    }

    @Test
    public void trySwitchErrorsForFirstName(){
        var request = requestInit();
        when(validator.validate(request))
                .thenReturn(List.of(new ValidationError("firstName", "null or empty")));

        var response = service.calculatePremium(request);
        assertTrue(response.hasErrors());
    }

    private TravelCalculatePremiumRequestV1 requestInit(){
        var request = new TravelCalculatePremiumRequestV1();
        request.setPersonFirstName("Vasia");
        request.setPersonLastName("Pupkin");
        request.setAgreementDateFrom(LocalDate.of(2023, 9, 12));
        request.setAgreementDateTo(LocalDate.of(2025, 2, 7));
        return request;
    }
}