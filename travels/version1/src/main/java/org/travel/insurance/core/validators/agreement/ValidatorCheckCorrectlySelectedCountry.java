package org.travel.insurance.core.validators.agreement;

import lombok.RequiredArgsConstructor;
import org.travel.insurance.core.repositories.ClassifierValueRepository;
import org.travel.insurance.core.util.Placeholder;
import org.travel.insurance.core.util.ValidationErrorFactory;
import org.travel.insurance.core.validators.ValidatorThrowableOneError;
import org.travel.insurance.dto.v1.TravelCalculatePremiumRequestV1;
import org.travel.insurance.dto.ValidationError;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class ValidatorCheckCorrectlySelectedCountry
        implements ValidatorThrowableOneError {

    private final ClassifierValueRepository repository;
    private final ValidationErrorFactory errorFactory;

    @Override
    public Optional<ValidationError> validate(TravelCalculatePremiumRequestV1 request) {
        String country = request.getCountry();

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

    private ValidationError buildError(String country){
        Placeholder placeholder = new Placeholder("NOT_EXISTING_COUNTRY_TYPE", country);
        return errorFactory.buildValidationError("ERROR_CODE_11", List.of(placeholder));
    }
}
