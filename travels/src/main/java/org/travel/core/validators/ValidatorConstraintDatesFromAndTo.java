package org.travel.core.validators;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.travel.dto.Request.TravelCalculatePremiumRequest;
import org.travel.dto.ValidationError;
import org.springframework.stereotype.Component;

import java.time.temporal.ChronoUnit;
import java.util.Optional;

@Component
@RequiredArgsConstructor(access = AccessLevel.PACKAGE)
class ValidatorConstraintDatesFromAndTo implements Validator {

    private Optional<ValidationError> coreConditionConstraint(TravelCalculatePremiumRequest request){
        long dif = ChronoUnit.DAYS.between(
                request.getAgreementDateFrom(),
                request.getAgreementDateTo());

        return dif >= 0 ? Optional.empty()
                : Optional.of(new ValidationError
                ("agreementDates", "Error: agreementDateTo before agreementDateFrom"));
    }

    private Optional<Boolean> check(TravelCalculatePremiumRequest request){
        if(request.getAgreementDateFrom() != null && request.getAgreementDateTo() != null)
            return Optional.empty();
        else
            return Optional.of(true);
    }// При успехе возвращаем пустой Optional

    @Override
    public Optional<ValidationError> validate(TravelCalculatePremiumRequest request) {
        return check(request)
                .map(x ->
                        new ValidationError(
                                "agreementDates",
                                "There is/are null or empty parameters")
                )
                .or(() -> coreConditionConstraint(request));
    }
}
