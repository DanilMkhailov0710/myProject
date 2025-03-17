package org.travel.insurance.core.validators.agreement;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import org.travel.insurance.core.util.ValidationErrorFactory;
import org.travel.insurance.core.validators.ValidatorThrowableOneError;
import org.travel.insurance.dto.ValidationError;
import org.travel.insurance.dto.v1.TravelCalculatePremiumRequestV1;

import java.util.Optional;

@Component
@RequiredArgsConstructor(access = AccessLevel.PACKAGE)
class ValidatorAgreementDateFrom implements ValidatorThrowableOneError {

    private final ValidationErrorFactory builderErrors;

    @Override
    public Optional<ValidationError> validate(TravelCalculatePremiumRequestV1 request) {
        return (request.getAgreementDateFrom() == null)
                ? Optional.of(builderErrors.buildValidationError("ERROR_CODE_3"))
                : Optional.empty();
    }

}
