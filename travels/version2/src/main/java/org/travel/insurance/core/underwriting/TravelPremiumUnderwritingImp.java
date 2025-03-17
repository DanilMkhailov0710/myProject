package org.travel.insurance.core.underwriting;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import org.travel.insurance.core.api.dto.RiskDTO;
import org.travel.insurance.core.api.dto.PersonDTO;
import org.travel.insurance.core.api.dto.AgreementDTO;

import java.math.BigDecimal;

@Component
@RequiredArgsConstructor(access = AccessLevel.PACKAGE)
class TravelPremiumUnderwritingImp implements TravelPremiumUnderwriting{

    private final SelectedRisksPremiumCalculator calculatorSelectedRisks;

    public TravelStoragePremiumSelectedRisks computePrice(AgreementDTO agreement, PersonDTO person){

        var risks = calculatorSelectedRisks.getSelectedRisks(agreement, person);

        BigDecimal totalPrice = risks.stream()
                .map(RiskDTO::getPremium)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        return new TravelStoragePremiumSelectedRisks(totalPrice, risks);
    }

}
