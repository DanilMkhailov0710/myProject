package org.travel.insurance.dto.v2.adapter;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import org.travel.insurance.core.api.dto.AgreementDTO;

import org.travel.insurance.dto.v2.TravelCalculatePremiumRequestV2;
import org.travel.insurance.dto.v2.adapter.converter_persons.ConverterPersonsDTOV2;

@Component
@RequiredArgsConstructor
class BuilderAgreementV2 {

    private final ConverterPersonsDTOV2 builderPersons;

    public AgreementDTO build(TravelCalculatePremiumRequestV2 request){
        var agreement = new AgreementDTO();
        agreement.setAgreementDateFrom(request.getAgreementDateFrom());
        agreement.setAgreementDateTo(request.getAgreementDateTo());
        agreement.setCountry(request.getCountry());
        agreement.setSelectedRisks(request.getSelectedRisks());
        agreement.setPersons(builderPersons.build(request));

        return agreement;
    }

}
