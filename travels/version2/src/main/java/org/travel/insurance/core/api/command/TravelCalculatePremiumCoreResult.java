package org.travel.insurance.core.api.command;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import org.travel.insurance.core.api.dto.AgreementDTO;
import org.travel.insurance.core.api.dto.ValidationErrorDTO;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TravelCalculatePremiumCoreResult {

    private List<ValidationErrorDTO> errors;

    private AgreementDTO agreement;

    public boolean hasErrors() {
        return errors != null && !errors.isEmpty();
    }

    public TravelCalculatePremiumCoreResult(List<ValidationErrorDTO> errors) {
        this.errors = errors;
    }

}
