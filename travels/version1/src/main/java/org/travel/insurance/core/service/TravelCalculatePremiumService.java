package org.travel.insurance.core.service;

import org.travel.insurance.dto.v1.TravelCalculatePremiumRequestV1;
import org.travel.insurance.dto.v1.TravelCalculatePremiumResponseV1;

public interface TravelCalculatePremiumService {

    TravelCalculatePremiumResponseV1 calculatePremium(TravelCalculatePremiumRequestV1 request);

}
