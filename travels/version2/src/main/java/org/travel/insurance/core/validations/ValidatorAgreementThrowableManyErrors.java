package org.travel.insurance.core.validations;

import org.travel.insurance.core.api.dto.AgreementDTO;
import org.travel.insurance.core.api.dto.ValidationErrorDTO;

import java.util.List;

public interface ValidatorAgreementThrowableManyErrors {
    List<ValidationErrorDTO> validate(AgreementDTO agreement);
}
