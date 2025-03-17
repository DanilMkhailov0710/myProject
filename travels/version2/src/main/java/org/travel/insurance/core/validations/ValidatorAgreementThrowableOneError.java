package org.travel.insurance.core.validations;

import org.travel.insurance.core.api.dto.AgreementDTO;
import org.travel.insurance.core.api.dto.ValidationErrorDTO;

import java.util.Optional;

public interface ValidatorAgreementThrowableOneError {

    Optional<ValidationErrorDTO> validate(AgreementDTO agreement);

}
