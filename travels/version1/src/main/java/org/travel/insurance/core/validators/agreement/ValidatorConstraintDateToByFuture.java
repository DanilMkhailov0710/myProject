package org.travel.insurance.core.validators.agreement;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import org.travel.insurance.core.util.ValidationErrorFactory;
import org.travel.insurance.core.validators.ValidatorThrowableOneError;

import org.travel.insurance.dto.ValidationError;
import org.travel.insurance.dto.v1.TravelCalculatePremiumRequestV1;

import java.util.Optional;
import java.time.LocalDate;

@Component
@RequiredArgsConstructor(access = AccessLevel.PACKAGE)
class ValidatorConstraintDateToByFuture implements ValidatorThrowableOneError {

    private final ValidationErrorFactory builderErrors;

    @Override
    public Optional<ValidationError> validate(TravelCalculatePremiumRequestV1 request) {
        LocalDate currentDate = LocalDate.now();
        LocalDate pointDate = request.getAgreementDateTo();

        return (pointDate == null || pointDate.isBefore(currentDate))
                ? Optional.of(builderErrors.buildValidationError("ERROR_CODE_6"))
                : Optional.empty();
    }

}
