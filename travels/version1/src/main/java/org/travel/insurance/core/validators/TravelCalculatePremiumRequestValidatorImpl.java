package org.travel.insurance.core.validators;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;

import org.travel.insurance.dto.v1.TravelCalculatePremiumRequestV1;
import org.travel.insurance.dto.ValidationError;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

@Component
@RequiredArgsConstructor(access = AccessLevel.PACKAGE)
class TravelCalculatePremiumRequestValidatorImpl implements TravelCalculatePremiumRequestValidator {

    private final List<ValidatorThrowableOneError> validatorThrowableOneError;
    private final List<ValidatorThrowableManyErrors> validatorThrowableManyErrors;

    public List<ValidationError> validate(TravelCalculatePremiumRequestV1 request) {
        return Stream
                .concat(collectErrorThrowableOne(request), collectErrorThrowableMany(request))
                .toList();
    }

    private Stream<ValidationError> collectErrorThrowableOne(TravelCalculatePremiumRequestV1 request){
        return validatorThrowableOneError
                .stream()
                .map(validator -> validator.validate(request))
                .filter(Optional::isPresent)
                .map(Optional::get);
    }

    private Stream<ValidationError> collectErrorThrowableMany(TravelCalculatePremiumRequestV1 request){
        return validatorThrowableManyErrors
                .stream()
                .map(validator -> validator.validate(request))
                .flatMap(Collection::stream);
    }
}
