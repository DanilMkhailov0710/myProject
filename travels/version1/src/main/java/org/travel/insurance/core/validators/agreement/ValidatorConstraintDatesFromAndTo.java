package org.travel.insurance.core.validators.agreement;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import org.travel.insurance.core.util.ValidationErrorFactory;
import org.travel.insurance.core.validators.ValidatorThrowableOneError;

import org.travel.insurance.dto.ValidationError;
import org.travel.insurance.dto.v1.TravelCalculatePremiumRequestV1;

import java.util.Optional;
import java.time.temporal.ChronoUnit;

@Component
@RequiredArgsConstructor(access = AccessLevel.PACKAGE)
class ValidatorConstraintDatesFromAndTo implements ValidatorThrowableOneError {

    private final ValidationErrorFactory builderErrors;

    private Optional<ValidationError> coreConditionConstraint(TravelCalculatePremiumRequestV1 request){
        long dif = ChronoUnit.DAYS.between(
                request.getAgreementDateFrom(),
                request.getAgreementDateTo());

        return dif >= 0
                ? Optional.empty()
                : Optional.of(builderErrors.buildValidationError("ERROR_CODE_7"));
    }

    private Optional<Boolean> check(TravelCalculatePremiumRequestV1 request){
        if(request.getAgreementDateFrom() != null && request.getAgreementDateTo() != null)
            return Optional.empty();
        else
            return Optional.of(true);
    }// При успехе возвращаем пустой Optional

    @Override
    public Optional<ValidationError> validate(TravelCalculatePremiumRequestV1 request) {
        return check(request)
                .map(x -> builderErrors.buildValidationError("ERROR_CODE_7")
                )
                .or(() -> coreConditionConstraint(request));
    }

}
