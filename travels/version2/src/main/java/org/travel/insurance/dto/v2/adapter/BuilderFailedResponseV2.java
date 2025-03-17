package org.travel.insurance.dto.v2.adapter;

import org.springframework.stereotype.Component;

import org.travel.insurance.core.api.command.TravelCalculatePremiumCoreResult;
import org.travel.insurance.dto.v2.TravelCalculatePremiumResponseV2;

import org.travel.insurance.dto.ValidationError;
import org.travel.insurance.core.api.dto.ValidationErrorDTO;

@Component
class BuilderFailedResponseV2 {

    TravelCalculatePremiumResponseV2 buildResponse(TravelCalculatePremiumCoreResult coreResult){
        var responseRisks = coreResult.getErrors()
                .stream()
                .map(this::createValidationError)
                .toList();

        var response = new TravelCalculatePremiumResponseV2(responseRisks);

        return response;
    }

    private ValidationError createValidationError(ValidationErrorDTO error){
        return new ValidationError(
                error.getErrorCode(),
                error.getDescription()
        );
    }

}
