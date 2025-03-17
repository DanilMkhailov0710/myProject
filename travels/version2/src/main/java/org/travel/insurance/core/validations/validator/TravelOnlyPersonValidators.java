package org.travel.insurance.core.validations.validator;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import org.travel.insurance.core.validations.ValidatorPersonThrowableManyErrors;
import org.travel.insurance.core.validations.ValidatorPersonThrowableOneError;

import org.travel.insurance.core.api.dto.PersonDTO;
import org.travel.insurance.core.api.dto.AgreementDTO;
import org.travel.insurance.core.api.dto.ValidationErrorDTO;

import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

@Component
@RequiredArgsConstructor
class TravelOnlyPersonValidators implements Validator{

    private final List<ValidatorPersonThrowableOneError> validatorOne;
    private final List<ValidatorPersonThrowableManyErrors> validatorMany;

    @Override
    public List<ValidationErrorDTO> validate(AgreementDTO agreement) {
        var persons = agreement.getPersons();

        if(persons == null || persons.isEmpty())
            List.of();

        var oneErrors = collectOneErrors(persons);
        var manyErrors = collectManyErrors(persons);

        return Stream.concat(oneErrors, manyErrors).toList();
    }

    private Stream<ValidationErrorDTO> collectOneErrors(List<PersonDTO> persons){
        if(validatorOne == null || validatorOne.isEmpty())
            return Stream.empty();

        return persons.stream()
                .flatMap(this::hideCollectOneErrors);
    }

    private Stream<ValidationErrorDTO> collectManyErrors(List<PersonDTO> persons){
        if(validatorMany == null || validatorMany.isEmpty())
            return Stream.empty();

        return persons.stream()
                .flatMap(this::hideCollectManyErrors);
    }

    private Stream<ValidationErrorDTO> hideCollectOneErrors(PersonDTO person){
        return validatorOne.stream()
                .map(val -> val.validate(person))
                .filter(Optional::isPresent)
                .map(Optional::get);
    }

    private Stream<ValidationErrorDTO> hideCollectManyErrors(PersonDTO person){
        return validatorMany.stream()
                .flatMap(x -> x.validate(person).stream());
    }

}
