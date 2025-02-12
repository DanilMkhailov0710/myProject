package org.travel.core.validators;

import org.travel.dto.Request.TravelCalculatePremiumRequest;
import org.travel.dto.ValidationError;

import java.util.List;

public interface TravelCalculatePremiumRequestValidator {

    public List<ValidationError> validate(TravelCalculatePremiumRequest request);

}
