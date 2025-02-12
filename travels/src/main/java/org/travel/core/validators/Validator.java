package org.travel.core.validators;

import org.travel.dto.Request.TravelCalculatePremiumRequest;
import org.travel.dto.ValidationError;

import java.util.Optional;

@FunctionalInterface
interface Validator {

    Optional<ValidationError> validate(TravelCalculatePremiumRequest request);

}
