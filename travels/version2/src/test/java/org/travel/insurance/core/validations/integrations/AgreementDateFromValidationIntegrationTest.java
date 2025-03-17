package org.travel.insurance.core.validations.integrations;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.travel.insurance.core.api.dto.PersonDTO;
import org.travel.insurance.core.api.dto.PersonDTOBuilder;
import org.travel.insurance.core.api.dto.AgreementDTOBuilder;
import org.travel.insurance.core.api.dto.ValidationErrorDTO;

import org.travel.insurance.core.validations.TravelAgreementValidator;

import java.util.List;
import java.time.LocalDate;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
public class AgreementDateFromValidationIntegrationTest {

    @Autowired private TravelAgreementValidator validator;

    @Test
    public void shouldReturnErrorWhenDateFromIsNull() {
        var person = createValidPerson();

        var agreement = AgreementDTOBuilder.createBuilder()
                .withAgreementDateFrom(null)
                .withAgreementDateTo(LocalDate.of(2030, 1, 1))
                .withCountry("SPAIN")
                .withSelectedRisks(List.of("TRAVEL_MEDICAL"))
                .withPersons(List.of(person))
                .build();

        List<ValidationErrorDTO> errors = validator.validate(agreement);

        assertEquals(3, errors.size());
    }

    @Test
    public void shouldReturnErrorWhenDateFromIsInThePast() {
        var person = createValidPerson();

        var agreement = AgreementDTOBuilder.createBuilder()
                .withAgreementDateFrom(LocalDate.of(2020, 1, 1))
                .withAgreementDateTo(LocalDate.of(2030, 1, 1))
                .withCountry("SPAIN")
                .withSelectedRisks(List.of("TRAVEL_MEDICAL"))
                .withPersons(List.of(person))
                .build();

        List<ValidationErrorDTO> errors = validator.validate(agreement);

        assertEquals(1, errors.size());
        assertEquals("ERROR_CODE_5", errors.get(0).getErrorCode());
    }

    private PersonDTO createValidPerson(){
        return PersonDTOBuilder.createBuilder()
                .withPersonFirstName("Vasja")
                .withPersonLastName("Pupkin")
                .withPersonBirthDate(LocalDate.of(2020, 1, 1))
                .withMedicalRiskLimitLevel("LEVEL_10000")
                .build();
    }

}
