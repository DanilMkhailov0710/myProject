package org.travel.insurance.core.validations.validator;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import org.travel.insurance.core.api.dto.AgreementDTO;
import org.travel.insurance.core.api.dto.ValidationErrorDTO;

import org.travel.insurance.core.validations.TravelAgreementValidator;

import java.util.List;

@Component
@RequiredArgsConstructor(access = AccessLevel.PACKAGE)
class TravelAgreementValidatorImpl implements TravelAgreementValidator {

    private final List<Validator> validators;

    @Override
    public List<ValidationErrorDTO> validate(AgreementDTO agreement) {
        return validators
                .stream()
                .flatMap(validator -> validator.validate(agreement).stream())
                .toList();
    }

}
