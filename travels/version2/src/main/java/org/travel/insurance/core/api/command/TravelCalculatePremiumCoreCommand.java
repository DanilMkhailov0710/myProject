package org.travel.insurance.core.api.command;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import org.travel.insurance.core.api.dto.AgreementDTO;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TravelCalculatePremiumCoreCommand {

    private AgreementDTO agreement;

}
