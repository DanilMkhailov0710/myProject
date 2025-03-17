package org.travel.insurance.core.api.dto;

import java.util.List;
import java.time.LocalDate;

public class PersonDTOBuilder {

    private String personFirstName;
    private String personLastName;
    private LocalDate personBirthDate;
    private String medicalRiskLimitLevel;
    private List<RiskDTO> risks;

    private PersonDTOBuilder(){}

    public static PersonDTOBuilder createBuilder(){
        return new PersonDTOBuilder();
    }

    public PersonDTO build(){
        var person = new PersonDTO();
        person.setPersonFirstName(personFirstName);
        person.setPersonLastName(personLastName);
        person.setPersonBirthDate(personBirthDate);
        person.setMedicalRiskLimitLevel(medicalRiskLimitLevel);
        person.setRisks(risks);

        return person;
    }

    public PersonDTOBuilder withPersonFirstName(String personFirstName) {
        this.personFirstName = personFirstName;
        return this;
    }

    public PersonDTOBuilder withPersonLastName(String personLastName) {
        this.personLastName = personLastName;
        return this;
    }

    public PersonDTOBuilder withPersonBirthDate(LocalDate personBirthDate) {
        this.personBirthDate = personBirthDate;
        return this;
    }

    public PersonDTOBuilder withMedicalRiskLimitLevel(String medicalRiskLimitLevel) {
        this.medicalRiskLimitLevel = medicalRiskLimitLevel;
        return this;
    }

    public PersonDTOBuilder withRisks(List<RiskDTO> risks) {
        this.risks = risks;
        return this;
    }

}
