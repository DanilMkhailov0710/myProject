package org.travel.insurance.core.validators.agreement;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import org.travel.insurance.core.util.ValidationErrorFactory;
import org.travel.insurance.core.validators.ValidatorThrowableOneError;
import org.travel.insurance.dto.ValidationError;
import org.travel.insurance.dto.v1.TravelCalculatePremiumRequestV1;

import java.util.Optional;

@Component
@RequiredArgsConstructor
class ValidatorCheckCountryNotNullBySelectedMedicalRisk
        implements ValidatorThrowableOneError {

    private final ValidationErrorFactory builderErrors;

    @Override
    public Optional<ValidationError> validate(TravelCalculatePremiumRequestV1 request) {
        String country = request.getCountry();

        return (country == null || country.isBlank())
                ? Optional.of(builderErrors.buildValidationError("ERROR_CODE_10"))
                : Optional.empty();
    }

}
