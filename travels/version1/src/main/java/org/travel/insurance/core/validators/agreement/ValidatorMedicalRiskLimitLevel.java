package org.travel.insurance.core.validators.agreement;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Value;

import org.travel.insurance.core.util.ValidationErrorFactory;
import org.travel.insurance.core.validators.ValidatorThrowableOneError;

import org.travel.insurance.dto.ValidationError;
import org.travel.insurance.dto.v1.TravelCalculatePremiumRequestV1;

import java.util.Optional;

@Component
@RequiredArgsConstructor(access = AccessLevel.PACKAGE)
class ValidatorMedicalRiskLimitLevel implements ValidatorThrowableOneError {

    @Value( "${medical.risk.limit.level.enabled}" )
    private Boolean medicalRiskLimitLevelEnabled;

    private final ValidationErrorFactory builderErrors;

    @Override
    public Optional<ValidationError> validate(TravelCalculatePremiumRequestV1 request) {
        if(!medicalRiskLimitLevelEnabled)
            return Optional.empty();

        var medicalRisk = request.getMedicalRiskLimitLevel();

        return (medicalRisk == null || medicalRisk.isEmpty())
                ? Optional.of(builderErrors.buildValidationError("ERROR_CODE_14"))
                : Optional.empty();
    }

}
