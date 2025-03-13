package org.travel.insurance.core.validators;

import org.travel.insurance.dto.v1.TravelCalculatePremiumRequestV1;
import org.travel.insurance.dto.ValidationError;
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
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class TravelCalculatePremiumRequestValidatorImplTest {
    @Mock
    private ValidatorThrowableOneError validator1;
    @Mock
    private ValidatorThrowableManyErrors validator11;
    @Mock
    private ValidatorThrowableOneError validator2;
    @Mock
    private ValidatorThrowableManyErrors validator22;
    @InjectMocks
    TravelCalculatePremiumRequestValidatorImpl genericValidator;

    private List<ValidationError> genericPart(TravelCalculatePremiumRequestV1 request){
        var travelValidationsOne = List.of(validator1, validator2);
        var travelValidationsMany = List.of(validator11, validator22);

        ReflectionTestUtils.setField(genericValidator, "validatorThrowableOneError", travelValidationsOne);
        ReflectionTestUtils.setField(genericValidator, "validatorThrowableManyErrors", travelValidationsMany);

        return genericValidator.validate(request);
    }

    @Test
    public void testIncorrectlyOne1(){
        var request = requestInit();
        when(validator1.validate(request)).thenReturn(Optional.of(new ValidationError("validator1", "fail")));
        assertEquals(1, genericPart(request).size());
    }

    @Test
    public void testIncorrectlyOne2(){
        var request = requestInit();
        when(validator2.validate(request)).thenReturn(Optional.of(new ValidationError("validator2", "fail")));
        assertEquals(1, genericPart(request).size());
    }

    @Test
    public void testEitherIncorrectly(){
        var request = requestInit();
        when(validator1.validate(request)).thenReturn(Optional.of(new ValidationError("validator1", "fail")));
        when(validator2.validate(request)).thenReturn(Optional.of(new ValidationError("validator2", "fail")));
        assertEquals(2, genericPart(request).size());
    }

    @Test
    public void testIncorrectlyOne3(){
        var request = requestInit();
        var errors = List.of(new ValidationError("1", "fail1"), new ValidationError("1", "fail2"));
        when(validator11.validate(request)).thenReturn(errors);
        assertEquals(2, genericPart(request).size());
    }

    @Test
    public void testIncorrectlyOne4(){
        var request = requestInit();
        var errors = List.of(new ValidationError("2", "fail1"), new ValidationError("2", "fail2"));
        when(validator22.validate(request)).thenReturn(errors);
        assertEquals(2, genericPart(request).size());
    }

    @Test
    public void testIncorrectlyOne5(){
        var request = requestInit();
        var errors1 = List.of(new ValidationError("1", "fail1"), new ValidationError("1", "fail2"));
        var errors2 = List.of(new ValidationError("2", "fail1"), new ValidationError("2", "fail2"));
        when(validator11.validate(request)).thenReturn(errors1);
        when(validator22.validate(request)).thenReturn(errors2);
        assertEquals(4, genericPart(request).size());
    }

    @Test
    public void testIncorrectlyOne6(){
        var request = requestInit();
        var errors1 = List.of(new ValidationError("1", "fail1"), new ValidationError("1", "fail2"));
        when(validator11.validate(request)).thenReturn(errors1);
        when(validator1.validate(request)).thenReturn(Optional.of(new ValidationError("err1", "fail1")));
        assertEquals(3, genericPart(request).size());
    }

    @Test
    public void testSuccessfully(){
        var request = requestInit();
        assertTrue(genericPart(request).isEmpty());
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