package org.travel.insurance.core.api.dto;

import java.math.BigDecimal;

public class RiskDTOBuilder {
    private String riskIc;
    private BigDecimal premium;

    private RiskDTOBuilder() {}

    public static RiskDTOBuilder createBuilder(){
        return new RiskDTOBuilder();
    }

    public RiskDTO build(){
        return new RiskDTO(riskIc, premium);
    }

    public RiskDTOBuilder setRiskIc(String riskIc) {
        this.riskIc = riskIc;
        return this;
    }

    public RiskDTOBuilder setPremium(BigDecimal premium) {
        this.premium = premium;
        return this;
    }
}
