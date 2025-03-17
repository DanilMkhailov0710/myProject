package org.travel.insurance.dto.v1.adapter;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import org.travel.insurance.dto.v1.TravelCalculatePremiumRequestV1;

import org.travel.insurance.core.api.dto.PersonDTO;
import org.travel.insurance.core.api.dto.AgreementDTO;

import java.util.List;

@Component
@RequiredArgsConstructor
class BuilderAgreementV1 {

    private final BuilderPersonV1 builderPerson;

    AgreementDTO build(TravelCalculatePremiumRequestV1 request) {
        var agreement = new AgreementDTO();
        agreement.setAgreementDateFrom(request.getAgreementDateFrom());
        agreement.setAgreementDateTo(request.getAgreementDateTo());
        agreement.setCountry(request.getCountry());
        agreement.setSelectedRisks(request.getSelectedRisks());

        PersonDTO person = builderPerson.build(request);
        agreement.setPersons(List.of(person));

        return agreement;
    }

}
