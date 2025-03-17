package org.travel.insurance.core.validators.agreement;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import org.travel.insurance.core.util.Placeholder;
import org.travel.insurance.core.util.ValidationErrorFactory;
import org.travel.insurance.core.validators.ValidatorThrowableManyErrors;
import org.travel.insurance.core.repositories.ClassifierValueRepository;

import org.travel.insurance.dto.ValidationError;
import org.travel.insurance.dto.v1.TravelCalculatePremiumRequestV1;

import java.util.List;

@Component
@RequiredArgsConstructor
class ValidatorCheckCorrectlySelectedRisks implements ValidatorThrowableManyErrors {

    private final ClassifierValueRepository repository;
    private final ValidationErrorFactory errorFactory;

    @Override
    public List<ValidationError> validate(TravelCalculatePremiumRequestV1 request) {
        if(request.getSelectedRisks() == null)
            return List.of(buildError("NULL"));

        return request.getSelectedRisks()
                .stream()
                .filter(this::isNotExistsInDB)
                .map(this::buildError)
                .toList();
    }

    private boolean isNotExistsInDB(String risk){
        return repository.findByClassifierTitleAndIc(
                "RISK_TYPE", risk)
                .isEmpty();
    }

    private ValidationError buildError(String risk){
        Placeholder placeholder = new Placeholder("NOT_EXISTING_RISK_TYPE", risk);
        return errorFactory.buildValidationError("ERROR_CODE_9", List.of(placeholder));
    }

}
