package org.travel.insurance.core.validations.person;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Value;

import org.travel.insurance.core.validations.ValidationErrorFactory;
import org.travel.insurance.core.validations.ValidatorPersonThrowableOneError;

import org.travel.insurance.core.api.dto.PersonDTO;
import org.travel.insurance.core.api.dto.ValidationErrorDTO;

import java.util.Optional;

@Component
@RequiredArgsConstructor
class ValidatorBirthDateEmpty implements ValidatorPersonThrowableOneError {

    @Value( "${medical.risk.age.coefficient}" )
    private Boolean medicalRiskAgeCoefficientEnabled;

    private final ValidationErrorFactory builderErrors;

    @Override
    public Optional<ValidationErrorDTO> validate(PersonDTO person) {
        if(!medicalRiskAgeCoefficientEnabled)
            return Optional.empty();

        return (person.getPersonBirthDate() == null)
                ? Optional.of(builderErrors.buildValidationError("ERROR_CODE_12"))
                : Optional.empty();
    }

}
