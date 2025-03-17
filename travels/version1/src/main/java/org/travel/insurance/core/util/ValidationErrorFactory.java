package org.travel.insurance.core.util;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import org.travel.insurance.dto.ValidationError;

import java.util.List;

@Component
@RequiredArgsConstructor
public class ValidationErrorFactory {

    private final ErrorCodeScanner scanner;

    public ValidationError buildValidationError(String errorCode){
        String descriptionError = scanner.getErrorDescription(errorCode);
        return new ValidationError(errorCode, descriptionError);
    }

    public ValidationError buildValidationError(String errorCode, List<Placeholder> placeholders) {
        String descriptionError = scanner.getErrorDescription(errorCode, placeholders);
        return new ValidationError(errorCode, descriptionError);
    }
}
