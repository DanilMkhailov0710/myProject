package org.travel.insurance.core.service;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;

import org.travel.insurance.core.underwriting.TravelPremiumUnderwriting;
import org.travel.insurance.core.validators.TravelCalculatePremiumRequestValidator;
import org.travel.insurance.dto.v1.TravelCalculatePremiumRequestV1;
import org.travel.insurance.dto.v1.TravelCalculatePremiumResponseV1;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor(access = AccessLevel.PACKAGE)
class TravelCalculatePremiumServiceImpl implements TravelCalculatePremiumService {

    private final TravelCalculatePremiumRequestValidator requestValidator;
    private final TravelPremiumUnderwriting calculatorPremium;

    @Override
    public TravelCalculatePremiumResponseV1 calculatePremium(TravelCalculatePremiumRequestV1 request) {
        var errors = requestValidator.validate(request);

        return !errors.isEmpty()
                ? new TravelCalculatePremiumResponseV1(errors)
                : buildResponse(request);
    }

    private TravelCalculatePremiumResponseV1 buildResponse(TravelCalculatePremiumRequestV1 request){
        var response = new TravelCalculatePremiumResponseV1();
        response.setPersonFirstName(request.getPersonFirstName());
        response.setPersonLastName(request.getPersonLastName());
        response.setPersonBirthDate(request.getPersonBirthDate());
        response.setAgreementDateFrom(request.getAgreementDateFrom());
        response.setAgreementDateTo(request.getAgreementDateTo());
        response.setCountry(request.getCountry());
        response.setMedicalRiskLimitLevel(request.getMedicalRiskLimitLevel());
        var storageRisks = calculatorPremium.computePrice(request);
        response.setAgreementPremium(storageRisks.totalPremium());
        response.setRisks(storageRisks.risks());
        return response;
    }
}
