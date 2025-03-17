package org.travel.insurance.core.validations.validator;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import org.travel.insurance.core.validations.ValidatorAgreementThrowableManyErrors;
import org.travel.insurance.core.validations.ValidatorAgreementThrowableOneError;

import org.travel.insurance.core.api.dto.AgreementDTO;
import org.travel.insurance.core.api.dto.ValidationErrorDTO;

import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

@Component
@RequiredArgsConstructor
public class TravelOnlyAgreementValidator implements Validator{

    private final List<ValidatorAgreementThrowableOneError> validatorOne;
    private final List<ValidatorAgreementThrowableManyErrors> validatorMany;

    @Override
    public List<ValidationErrorDTO> validate(AgreementDTO agreement) {
        return Stream.concat(
                        collectOneErrors(agreement),
                        collectManyErrors(agreement))
                .toList();
    }

    private Stream<ValidationErrorDTO> collectOneErrors(AgreementDTO agreement){
        if(validatorOne == null || validatorOne.isEmpty())
            return Stream.empty();

        return validatorOne
                .stream()
                .map(val -> val.validate(agreement))
                .filter(Optional::isPresent)
                .map(Optional::get);
    }

    private Stream<ValidationErrorDTO> collectManyErrors(AgreementDTO agreement){
        if(validatorMany == null || validatorMany.isEmpty())
            return Stream.empty();

        return validatorMany
                .stream()
                .flatMap(val -> val.validate(agreement).stream());
    }

}
