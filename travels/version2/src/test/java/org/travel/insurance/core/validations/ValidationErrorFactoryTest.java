package org.travel.insurance.core.validations;

import org.mockito.Mock;
import org.mockito.InjectMocks;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.travel.insurance.core.util.Placeholder;
import org.travel.insurance.core.util.ErrorCodeScanner;

import org.travel.insurance.core.api.dto.ValidationErrorDTO;

import java.util.List;

@ExtendWith(MockitoExtension.class)
class ValidationErrorFactoryTest {

    @Mock
    private ErrorCodeScanner errorCodeUtil;

    @InjectMocks
    private ValidationErrorFactory factory;

    @Test
    public void checkFirstMethod() {
        when(errorCodeUtil.getErrorDescription("ERROR_CODE"))
                .thenReturn("unique");
        ValidationErrorDTO error = factory.buildValidationError("ERROR_CODE");
        assertEquals(error.getErrorCode(), "ERROR_CODE");
        assertEquals(error.getDescription(), "unique");
    }

    @Test
    public void checkSecondMethod() {
        Placeholder placeholder = new Placeholder("PLACEHOLDER", "abrakadabra");
        when(errorCodeUtil.getErrorDescription("ERROR_CODE", List.of(placeholder)))
                .thenReturn("error abrakadabra description");
        ValidationErrorDTO error = factory.buildValidationError("ERROR_CODE", List.of(placeholder));
        assertEquals(error.getErrorCode(), "ERROR_CODE");
        assertEquals(error.getDescription(), "error abrakadabra description");
    }

}