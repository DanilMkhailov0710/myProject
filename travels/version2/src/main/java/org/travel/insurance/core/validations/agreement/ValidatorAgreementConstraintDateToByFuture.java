package org.travel.insurance.core.validations.agreement;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import org.travel.insurance.core.validations.ValidationErrorFactory;
import org.travel.insurance.core.validations.ValidatorAgreementThrowableOneError;

import org.travel.insurance.core.api.dto.AgreementDTO;
import org.travel.insurance.core.api.dto.ValidationErrorDTO;

import java.util.Optional;
import java.time.LocalDate;

@Component
@RequiredArgsConstructor(access = AccessLevel.PACKAGE)
class ValidatorAgreementConstraintDateToByFuture implements ValidatorAgreementThrowableOneError {

    private final ValidationErrorFactory builderErrors;

    @Override
    public Optional<ValidationErrorDTO> validate(AgreementDTO agreement) {
        LocalDate currentDate = LocalDate.now();
        LocalDate pointDate = agreement.getAgreementDateTo();

        return (pointDate == null || pointDate.isBefore(currentDate))
                ? Optional.of(builderErrors.buildValidationError("ERROR_CODE_6"))
                : Optional.empty();
    }

}
