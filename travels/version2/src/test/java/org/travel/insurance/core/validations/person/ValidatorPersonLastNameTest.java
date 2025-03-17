package org.travel.insurance.core.validations.person;

import org.mockito.Mock;
import org.mockito.InjectMocks;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.travel.insurance.core.api.dto.PersonDTO;
import org.travel.insurance.core.api.dto.ValidationErrorDTO;

import org.travel.insurance.core.validations.ValidationErrorFactory;

@ExtendWith(MockitoExtension.class)
class ValidatorPersonLastNameTest {

    @Mock private ValidationErrorFactory builderErrors;
    @InjectMocks private ValidatorPersonLastName validator;

    @Test
    public void testValidatorNull(){
        var request = new PersonDTO();
        settingsThrowError();
        var errors =  validator.validate(request);
        assertTrue(errors.isPresent());
    }

    @Test
    public void testValidatorEmpty(){
        var request = new PersonDTO();
        request.setPersonLastName("");
        request.setPersonFirstName("Vasia");
        settingsThrowError();
        var errors =  validator.validate(request);
        assertTrue(errors.isPresent());
    }

    @Test
    public void testValidatorIncorrectly(){
        var request = new PersonDTO();
        request.setPersonFirstName("Vasia");
        settingsThrowError();
        var errors =  validator.validate(request);
        assertTrue(errors.isPresent());
    }

    @Test
    public void testValidatorCorrectlyTwo(){
        var request = new PersonDTO();
        request.setPersonLastName("Pupkin");
        var errors =  validator.validate(request);
        assertTrue(errors.isEmpty());
    }

    private void settingsThrowError(){
        when(builderErrors.buildValidationError("ERROR_CODE_2"))
                .thenReturn(new ValidationErrorDTO("ERROR_CODE_2", "description2"));
    }

}