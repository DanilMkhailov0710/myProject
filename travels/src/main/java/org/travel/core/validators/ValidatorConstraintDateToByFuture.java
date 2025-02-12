package org.travel.core.validators;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.travel.dto.Request.TravelCalculatePremiumRequest;
import org.travel.dto.ValidationError;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.Optional;

@Component
@RequiredArgsConstructor(access = AccessLevel.PACKAGE)
class ValidatorConstraintDateToByFuture implements Validator {
    @Override
    public Optional<ValidationError> validate(TravelCalculatePremiumRequest request) {
        LocalDate currentDate = LocalDate.now();
        LocalDate pointDate = request.getAgreementDateTo();
        if(pointDate == null || pointDate.isBefore(currentDate))
            return Optional.of(
                    new ValidationError(
                            "agreementDateTo",
                            "The Date must be to future"));

        return Optional.empty();
    }
}
