package org.travel.insurance.core.validators;

import org.travel.insurance.dto.ValidationError;
import org.travel.insurance.dto.v1.TravelCalculatePremiumRequestV1;

import java.util.Optional;

public interface ValidatorThrowableOneError {

    Optional<ValidationError> validate(TravelCalculatePremiumRequestV1 request);

}
