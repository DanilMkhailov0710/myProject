package org.travel.core.validators;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.travel.dto.Request.TravelCalculatePremiumRequest;
import org.travel.dto.ValidationError;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor(access = AccessLevel.PACKAGE)
class TravelCalculatePremiumRequestValidatorImpl implements TravelCalculatePremiumRequestValidator {

    private final List<Validator> validators;

    public List<ValidationError> validate(TravelCalculatePremiumRequest request) {
        List<ValidationError> errors = new ArrayList<>();

        validators.forEach(validator -> validator
                            .validate(request)
                            .ifPresent(errors::add));

        return errors;
    }

}
