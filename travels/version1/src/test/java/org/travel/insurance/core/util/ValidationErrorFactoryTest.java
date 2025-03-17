package org.travel.insurance.core.util;

import org.mockito.Mock;
import org.mockito.InjectMocks;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.travel.insurance.dto.ValidationError;

import java.util.List;

@ExtendWith(MockitoExtension.class)
class ValidationErrorFactoryTest {

    @Mock private ErrorCodeScanner errorCodeUtil;
    @InjectMocks private ValidationErrorFactory factory;

    @Test
    public void checkFirstMethod() {
        when(errorCodeUtil.getErrorDescription("ERROR_CODE"))
                .thenReturn("unique");
        ValidationError error = factory.buildValidationError("ERROR_CODE");
        assertEquals(error.errorCode(), "ERROR_CODE");
        assertEquals(error.description(), "unique");
    }

    @Test
    public void checkSecondMethod() {
        Placeholder placeholder = new Placeholder("PLACEHOLDER", "abrakadabra");
        when(errorCodeUtil.getErrorDescription("ERROR_CODE", List.of(placeholder)))
                .thenReturn("error abrakadabra description");
        ValidationError error = factory.buildValidationError("ERROR_CODE", List.of(placeholder));
        assertEquals(error.errorCode(), "ERROR_CODE");
        assertEquals(error.description(), "error abrakadabra description");
    }

}