package org.core;

import org.rest.TravelCalculatePremiumRequest;
import org.rest.TravelCalculatePremiumResponse;
import org.springframework.stereotype.Component;

@Component
class TravelCalculatePremiumServiceImpl implements TravelCalculatePremiumService {

    @Override
    public TravelCalculatePremiumResponse calculatePremium(TravelCalculatePremiumRequest request) {
        return new TravelCalculatePremiumResponse(
                request.getPersonFirstName(),
                request.getPersonLastName(),
                request.getAgreementDateTo(),
                request.getAgreementDateFrom()
        );
    }
}
