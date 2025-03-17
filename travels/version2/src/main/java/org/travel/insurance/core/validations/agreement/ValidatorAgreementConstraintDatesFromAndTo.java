package org.travel.insurance.core.validations.agreement;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import org.travel.insurance.core.validations.ValidationErrorFactory;
import org.travel.insurance.core.validations.ValidatorAgreementThrowableOneError;

import org.travel.insurance.core.api.dto.AgreementDTO;
import org.travel.insurance.core.api.dto.ValidationErrorDTO;

import java.util.Optional;
import java.time.temporal.ChronoUnit;

@Component
@RequiredArgsConstructor(access = AccessLevel.PACKAGE)
class ValidatorAgreementConstraintDatesFromAndTo implements ValidatorAgreementThrowableOneError {

    private final ValidationErrorFactory builderErrors;

    private Optional<ValidationErrorDTO> coreConditionConstraint(AgreementDTO request){
        long dif = ChronoUnit.DAYS.between(
                request.getAgreementDateFrom(),
                request.getAgreementDateTo());

        return dif >= 0
                ? Optional.empty()
                : Optional.of(builderErrors.buildValidationError("ERROR_CODE_7"));
    }

    private Optional<Boolean> check(AgreementDTO request){
        if(request.getAgreementDateFrom() != null && request.getAgreementDateTo() != null)
            return Optional.empty();
        else
            return Optional.of(true);
    }// При успехе возвращаем пустой Optional

    @Override
    public Optional<ValidationErrorDTO> validate(AgreementDTO agreement) {
        return check(agreement)
                .map(x -> builderErrors.buildValidationError("ERROR_CODE_7")
                )
                .or(() -> coreConditionConstraint(agreement));
    }

}
