package org.travel.core;

import org.travel.dto.Request.TravelCalculatePremiumRequest;
import org.travel.dto.Response.TravelCalculatePremiumResponse;

public interface TravelCalculatePremiumService {

    TravelCalculatePremiumResponse calculatePremium(TravelCalculatePremiumRequest request);

}
