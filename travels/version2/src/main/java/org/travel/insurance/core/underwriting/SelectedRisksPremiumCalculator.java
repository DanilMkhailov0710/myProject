package org.travel.insurance.core.underwriting;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import org.travel.insurance.core.api.dto.RiskDTO;
import org.travel.insurance.core.api.dto.PersonDTO;
import org.travel.insurance.core.api.dto.AgreementDTO;

import java.util.List;

@Component
@RequiredArgsConstructor
class SelectedRisksPremiumCalculator {

    private final List<TravelRiskPremiumCalculator> travelRiskPremiumCalculators;

    public List<RiskDTO> getSelectedRisks(AgreementDTO request, PersonDTO person){

        List<String> selectedRisks = request.getSelectedRisks();

        return travelRiskPremiumCalculators
                .stream()
                .filter(risk -> selectedRisks.contains(risk.getRiskIc()))
                .map(risk -> new RiskDTO(risk.getRiskIc(), risk.calculatePremium(request, person)))
                .toList();
    }

}
