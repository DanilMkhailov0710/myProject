package org.travel.insurance.core.underwriting;

import org.travel.insurance.core.api.dto.PersonDTO;
import org.travel.insurance.core.api.dto.AgreementDTO;

import java.math.BigDecimal;

public interface TravelRiskPremiumCalculator {

    BigDecimal calculatePremium(AgreementDTO agreement, PersonDTO person);

    String getRiskIc();

}
