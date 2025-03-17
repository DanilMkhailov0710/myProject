package org.travel.insurance.core.validations.person;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import org.travel.insurance.core.validations.ValidationErrorFactory;
import org.travel.insurance.core.validations.ValidatorPersonThrowableOneError;

import org.travel.insurance.core.api.dto.PersonDTO;
import org.travel.insurance.core.api.dto.ValidationErrorDTO;

import java.util.Optional;

@Component
@RequiredArgsConstructor(access = AccessLevel.PACKAGE)
class ValidatorPersonFirstName implements ValidatorPersonThrowableOneError {

    private final ValidationErrorFactory builderErrors;

    @Override
    public Optional<ValidationErrorDTO> validate(PersonDTO person) {
        return (person.getPersonFirstName() == null || person.getPersonFirstName().isEmpty())
                ? Optional.of(builderErrors.buildValidationError("ERROR_CODE_1"))
                : Optional.empty();
    }

}
