package org.travel.insurance.core.validations;

import org.travel.insurance.core.api.dto.PersonDTO;
import org.travel.insurance.core.api.dto.ValidationErrorDTO;

import java.util.List;

public interface ValidatorPersonThrowableManyErrors {

    List<ValidationErrorDTO> validate(PersonDTO person);

}
