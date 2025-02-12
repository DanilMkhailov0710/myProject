package org.travel.core.validators;

import org.travel.dto.Request.TravelCalculatePremiumRequest;
import org.travel.dto.ValidationError;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class TravelCalculatePremiumRequestValidatorImplTest {
    @Mock
    private Validator validator1;
    @Mock
    private Validator validator2;
    @InjectMocks
    TravelCalculatePremiumRequestValidatorImpl genericValidator;

    @Test
    public void testEitherIncorrectly(){
        var request = requestInit();
        when(validator1.validate(request)).thenReturn(Optional.of(new ValidationError("validator1", "fail")));
        when(validator2.validate(request)).thenReturn(Optional.of(new ValidationError("validator2", "fail")));
        var travelValidations = List.of(validator1, validator2);

        ReflectionTestUtils.setField(genericValidator, "validators", travelValidations);

        var errors = genericValidator.validate(request);
        assertEquals(2, errors.size());
    }

    @Test
    public void testIncorrectlyOne1(){
        var request = requestInit();
        when(validator1.validate(request)).thenReturn(Optional.of(new ValidationError("validator1", "fail")));
        when(validator2.validate(request)).thenReturn(Optional.empty());
        var travelValidations = List.of(validator1, validator2);

        ReflectionTestUtils.setField(genericValidator, "validators", travelValidations);

        var errors = genericValidator.validate(request);
        assertEquals(1, errors.size());
    }

    @Test
    public void testIncorrectlyOne2(){
        var request = requestInit();
        when(validator1.validate(request)).thenReturn(Optional.empty());
        when(validator2.validate(request)).thenReturn(Optional.of(new ValidationError("validator2", "fail")));
        var travelValidations = List.of(validator1, validator2);

        ReflectionTestUtils.setField(genericValidator, "validators", travelValidations);

        var errors = genericValidator.validate(request);
        assertEquals(1, errors.size());
    }

    @Test
    public void testCorrectly(){
        var request = requestInit();
        when(validator1.validate(request)).thenReturn(Optional.empty());
        when(validator2.validate(request)).thenReturn(Optional.empty());
        var travelValidations = List.of(validator1, validator2);

        ReflectionTestUtils.setField(genericValidator, "validators", travelValidations);

        var errors = genericValidator.validate(request);
        assertEquals(0, errors.size());
    }

    private TravelCalculatePremiumRequest requestInit(){
        var request = new TravelCalculatePremiumRequest();
        request.setPersonFirstName("Vasia");
        request.setPersonLastName("Pupkin");
        request.setAgreementDateFrom(LocalDate.of(2023, 9, 12));
        request.setAgreementDateTo(LocalDate.of(2025, 2, 7));
        return request;
    }
}