package org.travel.core;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.travel.core.validators.TravelCalculatePremiumRequestValidator;
import org.travel.dto.Request.TravelCalculatePremiumRequest;
import org.travel.dto.Response.TravelCalculatePremiumResponse;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor(access = AccessLevel.PACKAGE)
public class TravelCalculatePremiumServiceImpl implements TravelCalculatePremiumService {

    private final TravelCalculatePremiumRequestValidator requestValidator;
    private final TravelCalculatorPremiumPrice calculatorPrice;

    @Override
    public TravelCalculatePremiumResponse calculatePremium(TravelCalculatePremiumRequest request) {
        var errors = requestValidator.validate(request);

        return !errors.isEmpty()
                ? new TravelCalculatePremiumResponse(errors)
                : buildResponse(request);
    }

    private TravelCalculatePremiumResponse buildResponse(TravelCalculatePremiumRequest request){
        var response = new TravelCalculatePremiumResponse();
        response.setPersonFirstName(request.getPersonFirstName());
        response.setPersonLastName(request.getPersonLastName());
        response.setAgreementDateFrom(request.getAgreementDateFrom());
        response.setAgreementDateTo(request.getAgreementDateTo());
        response.setAgreementPrice(calculatorPrice.computePrice(request));

        return response;
    }

}
