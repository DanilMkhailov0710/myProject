package org.travel.core.validators;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.travel.dto.Request.TravelCalculatePremiumRequest;
import org.travel.dto.ValidationError;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor(access = AccessLevel.PACKAGE)
class ValidatorPersonLastName implements Validator {
    @Override
    public Optional<ValidationError> validate(TravelCalculatePremiumRequest request) {
        return (request.getPersonLastName() == null || request.getPersonLastName().isEmpty())
                ? Optional.of(new ValidationError("personLastName", "Must not be empty!"))
                : Optional.empty();
    }
}
