package org.travel.insurance.dto.v1.adapter;

import org.springframework.stereotype.Component;

import org.travel.insurance.core.api.command.TravelCalculatePremiumCoreResult;

import org.travel.insurance.dto.ValidationError;
import org.travel.insurance.dto.v1.TravelCalculatePremiumResponseV1;

import java.util.List;

@Component
class BuilderFailedResponseV1 {

    TravelCalculatePremiumResponseV1 build(TravelCalculatePremiumCoreResult coreResult){
        var response = new TravelCalculatePremiumResponseV1();

        List<ValidationError> errors = coreResult
                .getErrors()
                .stream()
                .map(x -> new ValidationError(x.getErrorCode(), x.getDescription()))
                .toList();

        response.setErrors(errors);

        return response;
    }

}
