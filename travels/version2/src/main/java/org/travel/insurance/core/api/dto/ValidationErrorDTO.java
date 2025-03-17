package org.travel.insurance.core.api.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ValidationErrorDTO {

    private String errorCode;
    private String description;

}
