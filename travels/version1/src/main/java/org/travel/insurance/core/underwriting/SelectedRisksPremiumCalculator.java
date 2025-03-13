package org.travel.insurance.core.underwriting;

import lombok.RequiredArgsConstructor;

import org.travel.insurance.dto.v1.TravelCalculatePremiumRequestV1;
import org.travel.insurance.dto.Risk;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
class SelectedRisksPremiumCalculator {

    private final List<TravelRiskPremiumCalculator> travelRiskPremiumCalculators;

    public List<Risk> getSelectedRisks(TravelCalculatePremiumRequestV1 request){

        List<String> selectedRisks = request.getSelectedRisks();

        return travelRiskPremiumCalculators
                .stream()
                .filter(risk -> selectedRisks.contains(risk.getRiskIc()))
                .map(risk -> new Risk(risk.getRiskIc(), risk.calculatePremium(request)))
                .toList();
    }
}
