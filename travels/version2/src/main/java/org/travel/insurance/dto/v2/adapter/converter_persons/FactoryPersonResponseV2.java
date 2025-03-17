package org.travel.insurance.dto.v2.adapter.converter_persons;

import org.springframework.stereotype.Component;
import org.travel.insurance.dto.Risk;
import org.travel.insurance.dto.v2.PersonResponse;
import org.travel.insurance.core.api.dto.RiskDTO;
import org.travel.insurance.core.api.dto.PersonDTO;

import java.util.List;
import java.math.BigDecimal;

@Component
class FactoryPersonResponseV2 {

    PersonResponse build(PersonDTO person){
        var personResponse = new PersonResponse();
        personResponse.setPersonFirstName(person.getPersonFirstName());
        personResponse.setPersonLastName(person.getPersonLastName());
        personResponse.setPersonBirthDate(person.getPersonBirthDate());
        personResponse.setMedicalRiskLimitLevel(person.getMedicalRiskLimitLevel());

        var risksDTO = person.getRisks();
        var risks = mapper(risksDTO);
        BigDecimal personPremium = calculatePremium(risks);

        personResponse.setPersonPremium(personPremium);
        personResponse.setPersonRisks(risks);

        return personResponse;
    }

    private List<Risk> mapper(List<RiskDTO> risksDTO){
        return risksDTO.stream()
                .map(x -> new Risk(
                        x.getRiskIc(),
                        x.getPremium())
                )
                .toList();
    }

    private BigDecimal calculatePremium(List<Risk> risks){
        return risks.stream()
                .map(risk -> risk.getPremium())
                .reduce(BigDecimal.ZERO, (x, y) -> x.add(y));
    }

}
