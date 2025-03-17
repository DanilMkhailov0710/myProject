package org.travel.insurance.core.services;

import org.springframework.stereotype.Component;

import org.travel.insurance.core.api.dto.RiskDTO;
import org.travel.insurance.core.api.dto.PersonDTO;
import org.travel.insurance.core.api.dto.AgreementDTO;

import java.math.BigDecimal;
import java.util.Collection;

@Component
public class CalculatorTotalPrice {

    BigDecimal calculateTotalAgreementPremium(AgreementDTO agreement) {
        return agreement.getPersons().stream()
                .map(PersonDTO::getRisks)
                .flatMap(Collection::stream)
                .map(RiskDTO::getPremium)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

}
