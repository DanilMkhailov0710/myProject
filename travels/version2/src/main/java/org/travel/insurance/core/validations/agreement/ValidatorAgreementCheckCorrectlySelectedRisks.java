package org.travel.insurance.core.validations.agreement;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import org.travel.insurance.core.util.Placeholder;

import org.travel.insurance.core.validations.ValidationErrorFactory;
import org.travel.insurance.core.validations.ValidatorAgreementThrowableManyErrors;

import org.travel.insurance.core.api.dto.AgreementDTO;
import org.travel.insurance.core.api.dto.ValidationErrorDTO;

import org.travel.insurance.core.repositories.ClassifierValueRepository;

import java.util.List;

@Component
@RequiredArgsConstructor
class ValidatorAgreementCheckCorrectlySelectedRisks implements ValidatorAgreementThrowableManyErrors {

    private final ClassifierValueRepository repository;
    private final ValidationErrorFactory errorFactory;

    @Override
    public List<ValidationErrorDTO> validate(AgreementDTO agreement) {
        if(agreement.getSelectedRisks() == null)
            return List.of(buildError("NULL"));

        return agreement.getSelectedRisks()
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

    private ValidationErrorDTO buildError(String risk){
        Placeholder placeholder = new Placeholder("NOT_EXISTING_RISK_TYPE", risk);
        return errorFactory.buildValidationError("ERROR_CODE_9", List.of(placeholder));
    }

}
