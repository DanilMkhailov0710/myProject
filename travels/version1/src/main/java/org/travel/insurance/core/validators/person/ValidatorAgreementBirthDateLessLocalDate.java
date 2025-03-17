package org.travel.insurance.core.validators.person;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Value;

import org.travel.insurance.core.util.ValidationErrorFactory;
import org.travel.insurance.core.validators.ValidatorThrowableOneError;

import org.travel.insurance.dto.ValidationError;
import org.travel.insurance.dto.v1.TravelCalculatePremiumRequestV1;

import java.util.Optional;
import java.time.LocalDate;

@Component
@RequiredArgsConstructor
public class ValidatorAgreementBirthDateLessLocalDate
        implements ValidatorThrowableOneError {

    @Value( "${medical.risk.age.coefficient}" )
    private Boolean medicalRiskAgeCoefficientEnabled;

    private final ValidationErrorFactory builderErrors;

    @Override
    public Optional<ValidationError> validate(TravelCalculatePremiumRequestV1 request) {
        var personBirthDate = request.getPersonBirthDate();

        if(!medicalRiskAgeCoefficientEnabled && personBirthDate == null)
            return Optional.empty();

        return personBirthDate == null || personBirthDate.isAfter(LocalDate.now())
                ? Optional.of(builderErrors.buildValidationError("ERROR_CODE_13"))
                : Optional.empty();
    }

}
