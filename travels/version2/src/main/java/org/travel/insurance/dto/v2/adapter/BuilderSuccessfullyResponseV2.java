package org.travel.insurance.dto.v2.adapter;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import org.travel.insurance.core.api.command.TravelCalculatePremiumCoreResult;

import org.travel.insurance.dto.v2.TravelCalculatePremiumResponseV2;
import org.travel.insurance.dto.v2.adapter.converter_persons.ConverterPersonsDTOV2;

@Component
@RequiredArgsConstructor
class BuilderSuccessfullyResponseV2 {

    private final ConverterPersonsDTOV2 builderPersons;

    TravelCalculatePremiumResponseV2 buildResponse(TravelCalculatePremiumCoreResult coreResult){
        var agreement = coreResult.getAgreement();
        var response = new TravelCalculatePremiumResponseV2();
        response.setAgreementDateFrom(agreement.getAgreementDateFrom());
        response.setAgreementDateTo(agreement.getAgreementDateTo());
        response.setCountry(agreement.getCountry());
        response.setAgreementPremium(agreement.getAgreementPremium());

        var persons = builderPersons.build(coreResult);
        response.setPersons(persons);

        return response;
    }
}
