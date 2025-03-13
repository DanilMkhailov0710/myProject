package org.travel.insurance.core.underwriting;

import org.travel.insurance.dto.v1.TravelCalculatePremiumRequestV1;

public interface TravelPremiumUnderwriting {

    TravelStoragePremiumSelectedRisks computePrice(TravelCalculatePremiumRequestV1 request);

}
