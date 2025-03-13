package org.travel.insurance.core.validators.person;

import lombok.RequiredArgsConstructor;
import org.travel.insurance.core.util.ValidationErrorFactory;
import org.travel.insurance.core.validators.ValidatorThrowableOneError;
import org.travel.insurance.dto.v1.TravelCalculatePremiumRequestV1;
import org.travel.insurance.dto.ValidationError;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
class ValidatorBirthDateEmpty implements ValidatorThrowableOneError {
    @Value( "${medical.risk.age.coefficient}" )
    private Boolean medicalRiskAgeCoefficientEnabled;

    private final ValidationErrorFactory builderErrors;

    @Override
    public Optional<ValidationError> validate(TravelCalculatePremiumRequestV1 request) {
        if(!medicalRiskAgeCoefficientEnabled)
            return Optional.empty();

        return (request.getPersonBirthDate() == null)
                ? Optional.of(builderErrors.buildValidationError("ERROR_CODE_12"))
                : Optional.empty();
    }
}
