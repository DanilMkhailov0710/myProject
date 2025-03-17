package org.travel.insurance.core.validations.validator;

import org.mockito.Mock;
import org.mockito.InjectMocks;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.springframework.test.util.ReflectionTestUtils;

import org.travel.insurance.core.api.dto.PersonDTO;
import org.travel.insurance.core.api.dto.AgreementDTO;
import org.travel.insurance.core.api.dto.ValidationErrorDTO;

import org.travel.insurance.core.validations.ValidatorPersonThrowableManyErrors;
import org.travel.insurance.core.validations.ValidatorPersonThrowableOneError;

import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
class TravelOnlyPersonValidatorsTest {

    @Mock private ValidatorPersonThrowableOneError validatorOne1;
    @Mock private ValidatorPersonThrowableOneError validatorOne2;
    @Mock private ValidatorPersonThrowableManyErrors validatorMany1;
    @Mock private ValidatorPersonThrowableManyErrors validatorMany2;
    @InjectMocks private TravelOnlyPersonValidators validator;

    @Test
    public void test0(){
        PersonDTO person1 = new PersonDTO();
        PersonDTO person2 = new PersonDTO();
        when(validatorOne1.validate(person1)).thenReturn(Optional.of(new ValidationErrorDTO("ERROR_CODE", "1")));
        when(validatorOne1.validate(person2)).thenReturn(Optional.of(new ValidationErrorDTO("ERROR_CODE", "2")));
        when(validatorOne2.validate(person1)).thenReturn(Optional.of(new ValidationErrorDTO("ERROR_CODE", "3")));
        when(validatorOne2.validate(person2)).thenReturn(Optional.of(new ValidationErrorDTO("ERROR_CODE", "4")));
        when(validatorMany1.validate(person1)).thenReturn(List.of());
        when(validatorMany1.validate(person2)).thenReturn(List.of());
        when(validatorMany2.validate(person1)).thenReturn(List.of());
        when(validatorMany2.validate(person2)).thenReturn(List.of());
        var agreement = new AgreementDTO();
        agreement.setPersons(List.of(person1, person2));
        settingValidators(List.of(validatorOne1, validatorOne2), List.of(validatorMany1, validatorMany2));
        assertEquals(4, validator.validate(agreement).size());
    }

    @Test
    public void test1(){
        PersonDTO person1 = new PersonDTO();
        PersonDTO person2 = new PersonDTO();
        when(validatorOne1.validate(person1)).thenReturn(Optional.of(new ValidationErrorDTO("ERROR_CODE", "1")));
        when(validatorOne1.validate(person2)).thenReturn(Optional.of(new ValidationErrorDTO("ERROR_CODE", "2")));
        when(validatorOne2.validate(person1)).thenReturn(Optional.empty());
        when(validatorOne2.validate(person2)).thenReturn(Optional.empty());
        when(validatorMany1.validate(person1)).thenReturn(List.of());
        when(validatorMany1.validate(person2)).thenReturn(List.of());
        when(validatorMany2.validate(person1)).thenReturn(List.of());
        when(validatorMany2.validate(person2)).thenReturn(List.of());
        var agreement = new AgreementDTO();
        agreement.setPersons(List.of(person1, person2));
        settingValidators(List.of(validatorOne1, validatorOne2), List.of(validatorMany1, validatorMany2));
        assertEquals(2, validator.validate(agreement).size());
    }

    @Test
    public void test2(){
        PersonDTO person1 = new PersonDTO();
        PersonDTO person2 = new PersonDTO();
        when(validatorOne1.validate(person1)).thenReturn(Optional.empty());
        when(validatorOne1.validate(person2)).thenReturn(Optional.empty());
        when(validatorOne2.validate(person1)).thenReturn(Optional.empty());
        when(validatorOne2.validate(person2)).thenReturn(Optional.empty());
        when(validatorMany1.validate(person1)).thenReturn(List.of(new ValidationErrorDTO("ERROR_CODE", "1"), new ValidationErrorDTO("ERROR_CODE", "2")));
        when(validatorMany1.validate(person2)).thenReturn(List.of(new ValidationErrorDTO("ERROR_CODE", "3"), new ValidationErrorDTO("ERROR_CODE", "4")));
        when(validatorMany2.validate(person1)).thenReturn(List.of());
        when(validatorMany2.validate(person2)).thenReturn(List.of());
        var agreement = new AgreementDTO();
        agreement.setPersons(List.of(person1, person2));
        settingValidators(List.of(validatorOne1, validatorOne2), List.of(validatorMany1, validatorMany2));
        assertEquals(4, validator.validate(agreement).size());
    }

    @Test
    public void test3(){
        PersonDTO person1 = new PersonDTO();
        PersonDTO person2 = new PersonDTO();
        when(validatorOne1.validate(person1)).thenReturn(Optional.empty());
        when(validatorOne1.validate(person2)).thenReturn(Optional.empty());
        when(validatorOne2.validate(person1)).thenReturn(Optional.empty());
        when(validatorOne2.validate(person2)).thenReturn(Optional.empty());
        when(validatorMany1.validate(person1)).thenReturn(List.of(new ValidationErrorDTO("ERROR_CODE", "1"), new ValidationErrorDTO("ERROR_CODE", "2")));
        when(validatorMany1.validate(person2)).thenReturn(List.of());
        when(validatorMany2.validate(person1)).thenReturn(List.of());
        when(validatorMany2.validate(person2)).thenReturn(List.of(new ValidationErrorDTO("ERROR_CODE", "3"), new ValidationErrorDTO("ERROR_CODE", "4")));
        var agreement = new AgreementDTO();
        agreement.setPersons(List.of(person1, person2));
        settingValidators(List.of(validatorOne1, validatorOne2), List.of(validatorMany1, validatorMany2));
        assertEquals(4, validator.validate(agreement).size());
    }

    @Test
    public void test4(){
        PersonDTO person1 = new PersonDTO();
        PersonDTO person2 = new PersonDTO();
        when(validatorOne1.validate(person1)).thenReturn(Optional.of(new ValidationErrorDTO("ERROR_CODE", "9")));
        when(validatorOne1.validate(person2)).thenReturn(Optional.empty());
        when(validatorOne2.validate(person1)).thenReturn(Optional.empty());
        when(validatorOne2.validate(person2)).thenReturn(Optional.of(new ValidationErrorDTO("ERROR_CODE", "10")));
        when(validatorMany1.validate(person1)).thenReturn(List.of(new ValidationErrorDTO("ERROR_CODE", "1"), new ValidationErrorDTO("ERROR_CODE", "2")));
        when(validatorMany1.validate(person2)).thenReturn(List.of(new ValidationErrorDTO("ERROR_CODE", "3"), new ValidationErrorDTO("ERROR_CODE", "4")));
        when(validatorMany2.validate(person1)).thenReturn(List.of(new ValidationErrorDTO("ERROR_CODE", "5"), new ValidationErrorDTO("ERROR_CODE", "6")));
        when(validatorMany2.validate(person2)).thenReturn(List.of(new ValidationErrorDTO("ERROR_CODE", "7"), new ValidationErrorDTO("ERROR_CODE", "8")));
        var agreement = new AgreementDTO();
        agreement.setPersons(List.of(person1, person2));
        settingValidators(List.of(validatorOne1, validatorOne2), List.of(validatorMany1, validatorMany2));
        assertEquals(10, validator.validate(agreement).size());
    }

    @Test
    public void test5(){
        PersonDTO person1 = new PersonDTO();
        PersonDTO person2 = new PersonDTO();
        when(validatorOne1.validate(person1)).thenReturn(Optional.empty());
        when(validatorOne1.validate(person2)).thenReturn(Optional.empty());
        when(validatorOne2.validate(person1)).thenReturn(Optional.empty());
        when(validatorOne2.validate(person2)).thenReturn(Optional.empty());
        when(validatorMany1.validate(person1)).thenReturn(List.of());
        when(validatorMany1.validate(person2)).thenReturn(List.of());
        when(validatorMany2.validate(person1)).thenReturn(List.of());
        when(validatorMany2.validate(person2)).thenReturn(List.of());
        var agreement = new AgreementDTO();
        agreement.setPersons(List.of(person1, person2));
        settingValidators(List.of(validatorOne1, validatorOne2), List.of(validatorMany1, validatorMany2));
        assertEquals(0, validator.validate(agreement).size());
    }

    private void settingValidators(List<ValidatorPersonThrowableOneError> oneErrors, List<ValidatorPersonThrowableManyErrors> manyErrors){
        ReflectionTestUtils.setField(validator, "validatorOne", oneErrors);
        ReflectionTestUtils.setField(validator, "validatorMany", manyErrors);
    }

}