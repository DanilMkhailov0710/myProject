package org.travel.insurance.dto.v1.adapter;

import org.springframework.stereotype.Component;

import org.travel.insurance.core.api.command.TravelCalculatePremiumCoreResult;

import org.travel.insurance.dto.Risk;
import org.travel.insurance.dto.v1.TravelCalculatePremiumResponseV1;

import java.util.List;

@Component
class BuilderSuccessfullyResponseV1 {

    TravelCalculatePremiumResponseV1 build(TravelCalculatePremiumCoreResult coreResult){
        var agreement = coreResult.getAgreement();
        var person = agreement.getPersons().get(0);
        var response = new TravelCalculatePremiumResponseV1();

        response.setPersonFirstName(person.getPersonFirstName());
        response.setPersonLastName(person.getPersonLastName());
        response.setPersonBirthDate(person.getPersonBirthDate());
        response.setAgreementDateFrom(agreement.getAgreementDateFrom());
        response.setAgreementDateTo(agreement.getAgreementDateTo());
        response.setCountry(agreement.getCountry());
        response.setMedicalRiskLimitLevel(person.getMedicalRiskLimitLevel());
        response.setAgreementPremium(agreement.getAgreementPremium());

        List<Risk> risks = person.getRisks()
                .stream()
                .map(x -> new Risk(x.getRiskIc(), x.getPremium()))
                .toList();

        response.setRisks(risks);

        return response;
    }

}
