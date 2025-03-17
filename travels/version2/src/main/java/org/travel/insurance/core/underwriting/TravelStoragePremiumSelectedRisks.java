package org.travel.insurance.core.underwriting;

import org.travel.insurance.core.api.dto.RiskDTO;

import java.util.List;
import java.math.BigDecimal;

public record TravelStoragePremiumSelectedRisks(
        BigDecimal totalPremium,
        List<RiskDTO> risks
) {}
