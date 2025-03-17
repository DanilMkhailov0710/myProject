package org.travel.insurance.core.validations.agreement;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import org.travel.insurance.core.validations.ValidationErrorFactory;
import org.travel.insurance.core.validations.ValidatorAgreementThrowableOneError;

import org.travel.insurance.core.api.dto.AgreementDTO;
import org.travel.insurance.core.api.dto.ValidationErrorDTO;

import java.util.Optional;

@Component
@RequiredArgsConstructor
class ValidatorAgreementCheckCountryNotNullBySelectedMedicalRisk
        implements ValidatorAgreementThrowableOneError {

    private final ValidationErrorFactory builderErrors;

    @Override
    public Optional<ValidationErrorDTO> validate(AgreementDTO agreement) {
        String country = agreement.getCountry();

        return (country == null || country.isBlank())
                ? Optional.of(builderErrors.buildValidationError("ERROR_CODE_10"))
                : Optional.empty();
    }

}
