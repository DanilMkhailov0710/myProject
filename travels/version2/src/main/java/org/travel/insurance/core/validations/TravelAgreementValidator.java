package org.travel.insurance.core.validations;

import java.util.List;

import org.travel.insurance.core.api.dto.AgreementDTO;
import org.travel.insurance.core.api.dto.ValidationErrorDTO;

public interface TravelAgreementValidator {

    List<ValidationErrorDTO> validate(AgreementDTO agreement);

}
