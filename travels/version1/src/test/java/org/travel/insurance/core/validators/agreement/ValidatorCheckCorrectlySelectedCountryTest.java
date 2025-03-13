package org.travel.insurance.core.validators.agreement;

import org.travel.insurance.core.domain.ClassifierValue;
import org.travel.insurance.core.repositories.ClassifierValueRepository;
import org.travel.insurance.core.util.ValidationErrorFactory;
import org.travel.insurance.dto.v1.TravelCalculatePremiumRequestV1;
import org.travel.insurance.dto.ValidationError;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
class ValidatorCheckCorrectlySelectedCountryTest {
    @Mock private ClassifierValueRepository repository;
    @Mock private ValidationErrorFactory errorFactory;
    @InjectMocks private ValidatorCheckCorrectlySelectedCountry validator;

    @Test
    public void checkByNullCountry(){
        var request = new TravelCalculatePremiumRequestV1();
        assertTrue(validator.validate(request).isEmpty());
    }

    @Test
    public void first(){
        var request = new TravelCalculatePremiumRequestV1();
        request.setCountry("Grara");
        iniErrorFactory();
        assertTrue(validator.validate(request).isPresent());
    }

    @Test
    public void second(){
        var request = new TravelCalculatePremiumRequestV1();
        request.setCountry("JAPAN");
        when(repository.findByClassifierTitleAndIc("COUNTRY", "JAPAN")).thenReturn(Optional.of(new ClassifierValue()));
        assertTrue(validator.validate(request).isEmpty());
    }

    private void iniErrorFactory(){
        ValidationError validationError = new ValidationError("ERROR_CODE", "Unknown error");
        when(errorFactory.buildValidationError(eq("ERROR_CODE_11"), anyList()))
                .thenReturn(validationError);
    }
}