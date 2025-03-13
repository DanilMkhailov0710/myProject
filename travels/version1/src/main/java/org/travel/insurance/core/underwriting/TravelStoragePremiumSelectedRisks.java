package org.travel.insurance.core.underwriting;

import org.travel.insurance.dto.Risk;

import java.math.BigDecimal;
import java.util.List;

public record TravelStoragePremiumSelectedRisks(
        List<Risk> risks,
        BigDecimal totalPremium
) {}
