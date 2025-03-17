package org.travel.insurance.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

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
