package org.travel.insurance.core.underwriting;

import org.travel.insurance.dto.Risk;

import java.util.List;
import java.math.BigDecimal;

public record TravelStoragePremiumSelectedRisks(
        List<Risk> risks,
        BigDecimal totalPremium
) {}
