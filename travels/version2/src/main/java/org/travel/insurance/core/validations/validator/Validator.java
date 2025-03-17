package org.travel.insurance.core.validations.validator;

import org.travel.insurance.core.api.dto.AgreementDTO;
import org.travel.insurance.core.api.dto.ValidationErrorDTO;

import java.util.List;

interface Validator {

    List<ValidationErrorDTO> validate(AgreementDTO agreement);

}
