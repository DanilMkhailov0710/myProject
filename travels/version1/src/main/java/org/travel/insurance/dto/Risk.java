package org.travel.insurance.dto;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.travel.insurance.dto.util.BigDecimalSerialize;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Risk {
    private String riskIc;
    @JsonSerialize(using = BigDecimalSerialize.class)
    private BigDecimal premium;
}
