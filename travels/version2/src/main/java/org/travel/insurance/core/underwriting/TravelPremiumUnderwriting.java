package org.travel.insurance.core.underwriting;

import org.travel.insurance.core.api.dto.PersonDTO;
import org.travel.insurance.core.api.dto.AgreementDTO;

public interface TravelPremiumUnderwriting {

    TravelStoragePremiumSelectedRisks computePrice(AgreementDTO agreement, PersonDTO person);

}
