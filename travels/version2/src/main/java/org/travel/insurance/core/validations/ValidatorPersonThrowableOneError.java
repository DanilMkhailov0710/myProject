package org.travel.insurance.core.validations;

import org.travel.insurance.core.api.dto.PersonDTO;
import org.travel.insurance.core.api.dto.ValidationErrorDTO;

import java.util.Optional;

public interface ValidatorPersonThrowableOneError {

    Optional<ValidationErrorDTO> validate(PersonDTO person);

}
