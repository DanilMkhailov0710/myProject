package org.travel.insurance.core.validations.validator;

import org.mockito.Mock;
import org.mockito.InjectMocks;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.springframework.test.util.ReflectionTestUtils;

import org.travel.insurance.core.api.dto.AgreementDTO;
import org.travel.insurance.core.api.dto.ValidationErrorDTO;

import org.travel.insurance.core.validations.ValidatorAgreementThrowableManyErrors;
import org.travel.insurance.core.validations.ValidatorAgreementThrowableOneError;

import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
class TravelOnlyAgreementValidatorTest {

    @Mock private ValidatorAgreementThrowableOneError validatorOne1;
    @Mock private ValidatorAgreementThrowableOneError validatorOne2;
    @Mock private ValidatorAgreementThrowableManyErrors validatorMany1;
    @Mock private ValidatorAgreementThrowableManyErrors validatorMany2;
    @InjectMocks private TravelOnlyAgreementValidator validator;

    @Test
    public void test0(){
        var agreement = new AgreementDTO();
        when(validatorOne1.validate(agreement)).thenReturn(Optional.of(new ValidationErrorDTO("ERROR_CODE", "1")));
        when(validatorMany1.validate(agreement)).thenReturn(List.of(new ValidationErrorDTO("ERROR_CODE", "2")));
        when(validatorOne2.validate(agreement)).thenReturn(Optional.empty());
        when(validatorMany2.validate(agreement)).thenReturn(List.of());
        settingValidators(List.of(validatorOne1, validatorOne2), List.of(validatorMany1, validatorMany2));
        var errors = validator.validate(agreement);
        assertEquals(2, errors.size());
    }

    @Test
    public void test1(){
        var agreement = new AgreementDTO();
        when(validatorOne1.validate(agreement)).thenReturn(Optional.of(new ValidationErrorDTO("ERROR_CODE", "1")));
        when(validatorMany1.validate(agreement)).thenReturn(List.of());
        when(validatorOne2.validate(agreement)).thenReturn(Optional.of(new ValidationErrorDTO("ERROR_CODE", "2")));
        when(validatorMany2.validate(agreement)).thenReturn(List.of());
        settingValidators(List.of(validatorOne1, validatorOne2), List.of(validatorMany1, validatorMany2));
        var errors = validator.validate(agreement);
        assertEquals(2, errors.size());
    }

    @Test
    public void test2(){
        var agreement = new AgreementDTO();
        when(validatorOne1.validate(agreement)).thenReturn(Optional.of(new ValidationErrorDTO("ERROR_CODE", "1")));
        when(validatorMany1.validate(agreement)).thenReturn(List.of());
        when(validatorOne2.validate(agreement)).thenReturn(Optional.empty());
        when(validatorMany2.validate(agreement)).thenReturn(List.of());
        settingValidators(List.of(validatorOne1, validatorOne2), List.of(validatorMany1, validatorMany2));
        var errors = validator.validate(agreement);
        assertEquals(1, errors.size());
    }

    @Test
    public void test3(){
        var agreement = new AgreementDTO();
        when(validatorOne1.validate(agreement)).thenReturn(Optional.empty());
        when(validatorMany1.validate(agreement)).thenReturn(List.of(new ValidationErrorDTO("ERROR_CODE", "1"), new ValidationErrorDTO("ERROR_CODE", "2")));
        when(validatorOne2.validate(agreement)).thenReturn(Optional.empty());
        when(validatorMany2.validate(agreement)).thenReturn(List.of());
        settingValidators(List.of(validatorOne1, validatorOne2), List.of(validatorMany1, validatorMany2));
        var errors = validator.validate(agreement);
        assertEquals(2, errors.size());
    }

    @Test
    public void test4(){
        var agreement = new AgreementDTO();
        when(validatorOne1.validate(agreement)).thenReturn(Optional.empty());
        when(validatorMany1.validate(agreement)).thenReturn(List.of(new ValidationErrorDTO("ERROR_CODE", "1"), new ValidationErrorDTO("ERROR_CODE", "2")));
        when(validatorOne2.validate(agreement)).thenReturn(Optional.empty());
        when(validatorMany2.validate(agreement)).thenReturn(List.of(new ValidationErrorDTO("ERROR_CODE", "3"), new ValidationErrorDTO("ERROR_CODE", "4")));
        settingValidators(List.of(validatorOne1, validatorOne2), List.of(validatorMany1, validatorMany2));
        var errors = validator.validate(agreement);
        assertEquals(4, errors.size());
    }

    @Test
    public void test5(){
        var agreement = new AgreementDTO();
        when(validatorOne1.validate(agreement)).thenReturn(Optional.of(new ValidationErrorDTO("ERROR_CODE", "5")));
        when(validatorMany1.validate(agreement)).thenReturn(List.of(new ValidationErrorDTO("ERROR_CODE", "1"), new ValidationErrorDTO("ERROR_CODE", "2")));
        when(validatorOne2.validate(agreement)).thenReturn(Optional.of(new ValidationErrorDTO("ERROR_CODE", "6")));
        when(validatorMany2.validate(agreement)).thenReturn(List.of(new ValidationErrorDTO("ERROR_CODE", "3"), new ValidationErrorDTO("ERROR_CODE", "4")));
        settingValidators(List.of(validatorOne1, validatorOne2), List.of(validatorMany1, validatorMany2));
        var errors = validator.validate(agreement);
        assertEquals(6, errors.size());
    }

    @Test
    public void test6(){
        var agreement = new AgreementDTO();
        when(validatorOne1.validate(agreement)).thenReturn(Optional.empty());
        when(validatorMany1.validate(agreement)).thenReturn(List.of());
        when(validatorOne2.validate(agreement)).thenReturn(Optional.empty());
        when(validatorMany2.validate(agreement)).thenReturn(List.of());
        settingValidators(List.of(validatorOne1, validatorOne2), List.of(validatorMany1, validatorMany2));
        var errors = validator.validate(agreement);
        assertEquals(0, errors.size());
    }

    private void settingValidators(List<ValidatorAgreementThrowableOneError> oneErrors, List<ValidatorAgreementThrowableManyErrors> manyErrors){
        ReflectionTestUtils.setField(validator, "validatorOne", oneErrors);
        ReflectionTestUtils.setField(validator, "validatorMany", manyErrors);
    }

}