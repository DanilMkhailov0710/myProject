package org.travel.insurance.core.validations.agreement;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import org.travel.insurance.core.validations.ValidationErrorFactory;
import org.travel.insurance.core.validations.ValidatorAgreementThrowableOneError;

import org.travel.insurance.core.api.dto.AgreementDTO;
import org.travel.insurance.core.api.dto.ValidationErrorDTO;

import java.util.Optional;

@Component
@RequiredArgsConstructor(access = AccessLevel.PACKAGE)
class ValidatorAgreementAgreementDateTo implements ValidatorAgreementThrowableOneError {

    private final ValidationErrorFactory builderErrors;

    @Override
    public Optional<ValidationErrorDTO> validate(AgreementDTO agreement) {
        return (agreement.getAgreementDateTo() == null)
                ? Optional.of(builderErrors.buildValidationError("ERROR_CODE_4"))
                : Optional.empty();
    }

}
