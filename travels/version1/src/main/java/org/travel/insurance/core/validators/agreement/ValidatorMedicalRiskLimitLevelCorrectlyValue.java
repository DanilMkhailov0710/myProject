package org.travel.insurance.core.validators.agreement;

import lombok.RequiredArgsConstructor;
import org.travel.insurance.core.repositories.MedicalRiskLimitLevelRepository;
import org.travel.insurance.core.util.Placeholder;
import org.travel.insurance.core.util.ValidationErrorFactory;
import org.travel.insurance.core.validators.ValidatorThrowableOneError;
import org.travel.insurance.dto.v1.TravelCalculatePremiumRequestV1;
import org.travel.insurance.dto.ValidationError;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class ValidatorMedicalRiskLimitLevelCorrectlyValue
        implements ValidatorThrowableOneError {

    @Value( "${medical.risk.limit.level.enabled}" )
    private Boolean medicalRiskLimitLevelEnabled;

    private final ValidationErrorFactory builderErrors;
    private final MedicalRiskLimitLevelRepository repository;

    @Override
    public Optional<ValidationError> validate(TravelCalculatePremiumRequestV1 request) {
        var medicalRisk = request.getMedicalRiskLimitLevel();

        if(!medicalRiskLimitLevelEnabled && medicalRisk == null)
            return Optional.empty();

        if (medicalRisk == null)
            return throwError("NULL");

        return (repository.findByMedicalRiskLimitLevelIc(medicalRisk).isEmpty())
                ? throwError(medicalRisk)
                : Optional.empty();
    }

    private Optional<ValidationError> throwError(String medicalRisk){
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
