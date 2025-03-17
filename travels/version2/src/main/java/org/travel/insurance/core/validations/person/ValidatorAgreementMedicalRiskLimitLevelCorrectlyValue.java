package org.travel.insurance.core.validations.person;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Value;

import org.travel.insurance.core.util.Placeholder;

import org.travel.insurance.core.validations.ValidationErrorFactory;
import org.travel.insurance.core.validations.ValidatorPersonThrowableOneError;

import org.travel.insurance.core.api.dto.PersonDTO;
import org.travel.insurance.core.api.dto.ValidationErrorDTO;

import org.travel.insurance.core.repositories.MedicalRiskLimitLevelRepository;

import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class ValidatorAgreementMedicalRiskLimitLevelCorrectlyValue
        implements ValidatorPersonThrowableOneError {

    @Value( "${medical.risk.limit.level.enabled}" )
    private Boolean medicalRiskLimitLevelEnabled;

    private final ValidationErrorFactory builderErrors;
    private final MedicalRiskLimitLevelRepository repository;

    @Override
    public Optional<ValidationErrorDTO> validate(PersonDTO person) {
        var medicalRisk = person.getMedicalRiskLimitLevel();

        if(!medicalRiskLimitLevelEnabled && medicalRisk == null)
            return Optional.empty();

        if (medicalRisk == null)
            return throwError("NULL");

        return (repository.findByMedicalRiskLimitLevelIc(medicalRisk).isEmpty())
                ? throwError(medicalRisk)
                : Optional.empty();
    }

    private Optional<ValidationErrorDTO> throwError(String medicalRisk){
        var placeholderName =
                "NOT_EXISTING_MEDICAL_RISC_LIMIT_LEVEL_IC";

        var placeholder = new Placeholder(
                placeholderName,
                medicalRisk);

        var error = builderErrors.buildValidationError(
                "ERROR_CODE_15",
                List.of(placeholder));

        return Optional.of(error);
    }

}
