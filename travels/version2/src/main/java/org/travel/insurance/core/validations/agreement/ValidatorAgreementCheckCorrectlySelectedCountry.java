package org.travel.insurance.core.validations.agreement;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import org.travel.insurance.core.util.Placeholder;

import org.travel.insurance.core.validations.ValidationErrorFactory;
import org.travel.insurance.core.validations.ValidatorAgreementThrowableOneError;

import org.travel.insurance.core.api.dto.AgreementDTO;
import org.travel.insurance.core.api.dto.ValidationErrorDTO;

import org.travel.insurance.core.repositories.ClassifierValueRepository;

import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
class ValidatorAgreementCheckCorrectlySelectedCountry
        implements ValidatorAgreementThrowableOneError {

    private final ClassifierValueRepository repository;
    private final ValidationErrorFactory errorFactory;

    @Override
    public Optional<ValidationErrorDTO> validate(AgreementDTO agreement) {
        String country = agreement.getCountry();

        if(country == null)
            return Optional.empty();

        return isExistsInDB(country)
                ? Optional.empty()
                : Optional.of(buildError(country));
    }

    private boolean isExistsInDB(String country){
        return repository.findByClassifierTitleAndIc(
                        "COUNTRY", country)
                .isPresent();
    }

    private ValidationErrorDTO buildError(String country){
        Placeholder placeholder = new Placeholder("NOT_EXISTING_COUNTRY_TYPE", country);
        return errorFactory.buildValidationError("ERROR_CODE_11", List.of(placeholder));
    }

}
