package org.travel.insurance.core.validations.person;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Value;

import org.travel.insurance.core.validations.ValidationErrorFactory;
import org.travel.insurance.core.validations.ValidatorPersonThrowableOneError;

import org.travel.insurance.core.api.dto.PersonDTO;
import org.travel.insurance.core.api.dto.ValidationErrorDTO;

import java.util.Optional;

@Component
@RequiredArgsConstructor(access = AccessLevel.PACKAGE)
class ValidatorAgreementMedicalRiskLimitLevel implements ValidatorPersonThrowableOneError {

    @Value( "${medical.risk.limit.level.enabled}" )
    private Boolean medicalRiskLimitLevelEnabled;

    private final ValidationErrorFactory builderErrors;

    @Override
    public Optional<ValidationErrorDTO> validate(PersonDTO person) {
        if(!medicalRiskLimitLevelEnabled)
            return Optional.empty();

        var medicalRisk = person.getMedicalRiskLimitLevel();

        return (medicalRisk == null || medicalRisk.isEmpty())
                ? Optional.of(builderErrors.buildValidationError("ERROR_CODE_14"))
                : Optional.empty();
    }

}
