package org.travel.insurance.dto.v2;

import lombok.Getter;
import lombok.Setter;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PersonRequest {

    private String personFirstName;
    private String personLastName;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate personBirthDate;

    private String medicalRiskLimitLevel;

}
