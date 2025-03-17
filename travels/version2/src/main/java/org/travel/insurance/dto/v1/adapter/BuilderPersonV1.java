package org.travel.insurance.dto.v1.adapter;

import org.springframework.stereotype.Component;

import org.travel.insurance.dto.v1.TravelCalculatePremiumRequestV1;

import org.travel.insurance.core.api.dto.PersonDTO;

@Component
class BuilderPersonV1 {

    PersonDTO build(TravelCalculatePremiumRequestV1 request) {
        var person = new PersonDTO();
        person.setPersonFirstName(request.getPersonFirstName());
        person.setPersonLastName(request.getPersonLastName());
        person.setPersonBirthDate(request.getPersonBirthDate());
        person.setMedicalRiskLimitLevel(request.getMedicalRiskLimitLevel());

        return person;
    }

}
