package org.travel.insurance.core.validations.person;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Value;

import org.travel.insurance.core.validations.ValidationErrorFactory;
import org.travel.insurance.core.validations.ValidatorPersonThrowableOneError;

import org.travel.insurance.core.api.dto.PersonDTO;
import org.travel.insurance.core.api.dto.ValidationErrorDTO;

import java.util.Optional;
import java.time.LocalDate;

@Component
@RequiredArgsConstructor
public class ValidatorAgreementBirthDateLessLocalDate
        implements ValidatorPersonThrowableOneError {

    @Value( "${medical.risk.age.coefficient}" )
    private Boolean medicalRiskAgeCoefficientEnabled;

    private final ValidationErrorFactory builderErrors;

    @Override
    public Optional<ValidationErrorDTO> validate(PersonDTO person) {
        var personBirthDate = person.getPersonBirthDate();

        if(!medicalRiskAgeCoefficientEnabled && personBirthDate == null)
            return Optional.empty();

        return personBirthDate == null || personBirthDate.isAfter(LocalDate.now())
                ? Optional.of(builderErrors.buildValidationError("ERROR_CODE_13"))
                : Optional.empty();
    }

}
