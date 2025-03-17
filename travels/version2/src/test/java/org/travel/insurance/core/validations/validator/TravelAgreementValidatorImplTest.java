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

import java.util.List;

@ExtendWith(MockitoExtension.class)
class TravelAgreementValidatorImplTest {

    @Mock private Validator validator1;
    @Mock private Validator validator2;
    @InjectMocks private TravelAgreementValidatorImpl validator;

    @Test
    public void test0(){
        var agreement = new AgreementDTO();
        when(validator1.validate(agreement)).thenReturn(List.of(new ValidationErrorDTO("ERROR_CODE", "1")));
        when(validator2.validate(agreement)).thenReturn(List.of(new ValidationErrorDTO("ERROR_CODE", "2")));
        settingValidators(List.of(validator1, validator2));
        assertEquals(2, validator.validate(agreement).size());
    }

    @Test
    public void test1(){
        var agreement = new AgreementDTO();
        when(validator1.validate(agreement)).thenReturn(List.of(new ValidationErrorDTO("ERROR_CODE", "1"), new ValidationErrorDTO("ERROR_CODE", "2")));
        when(validator2.validate(agreement)).thenReturn(List.of());
        settingValidators(List.of(validator1, validator2));
        assertEquals(2, validator.validate(agreement).size());
    }

    @Test
    public void test2(){
        var agreement = new AgreementDTO();
        when(validator1.validate(agreement)).thenReturn(List.of(new ValidationErrorDTO("ERROR_CODE", "1"), new ValidationErrorDTO("ERROR_CODE", "2")));
        when(validator2.validate(agreement)).thenReturn(List.of(new ValidationErrorDTO("ERROR_CODE", "3")));
        settingValidators(List.of(validator1, validator2));
        assertEquals(3, validator.validate(agreement).size());
    }

    @Test
    public void test3(){
        var agreement = new AgreementDTO();
        when(validator1.validate(agreement)).thenReturn(List.of());
        when(validator2.validate(agreement)).thenReturn(List.of());
        settingValidators(List.of(validator1, validator2));
        assertEquals(0, validator.validate(agreement).size());
    }

    private void settingValidators(List<Validator> validators){
        ReflectionTestUtils.setField(validator, "validators", validators);
    }

}