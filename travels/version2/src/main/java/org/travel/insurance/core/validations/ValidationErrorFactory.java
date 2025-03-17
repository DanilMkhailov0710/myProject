package org.travel.insurance.core.validations;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import org.travel.insurance.core.util.Placeholder;
import org.travel.insurance.core.util.ErrorCodeScanner;

import org.travel.insurance.core.api.dto.ValidationErrorDTO;

import java.util.List;

@Component
@RequiredArgsConstructor
public class ValidationErrorFactory {

    private final ErrorCodeScanner scanner;

    public ValidationErrorDTO buildValidationError(String errorCode){
        String descriptionError = scanner.getErrorDescription(errorCode);
        return new ValidationErrorDTO(errorCode, descriptionError);
    }

    public ValidationErrorDTO buildValidationError(String errorCode, List<Placeholder> placeholders) {
        String descriptionError = scanner.getErrorDescription(errorCode, placeholders);
        return new ValidationErrorDTO(errorCode, descriptionError);
    }

}
