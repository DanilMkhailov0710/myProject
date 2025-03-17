package org.travel.insurance.dto.v2.adapter.converter_persons;

import org.springframework.stereotype.Component;

import org.travel.insurance.dto.v2.PersonRequest;
import org.travel.insurance.core.api.dto.PersonDTO;

@Component
class FactoryPersonDTOV2 {

     PersonDTO build(PersonRequest personRequest){
        var person = new PersonDTO();
        person.setPersonFirstName(personRequest.getPersonFirstName());
        person.setPersonLastName(personRequest.getPersonLastName());
        person.setPersonBirthDate(personRequest.getPersonBirthDate());
        person.setMedicalRiskLimitLevel(personRequest.getMedicalRiskLimitLevel());

        return person;
    }

}
