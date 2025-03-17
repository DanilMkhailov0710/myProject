package org.travel.insurance.dto.v2.adapter.converter_persons;

import org.travel.insurance.dto.v2.PersonResponse;
import org.travel.insurance.core.api.dto.PersonDTO;
import org.travel.insurance.dto.v2.TravelCalculatePremiumRequestV2;
import org.travel.insurance.core.api.command.TravelCalculatePremiumCoreResult;

import java.util.List;

public interface ConverterPersonsDTOV2 {

    List<PersonDTO> build(TravelCalculatePremiumRequestV2 request);

    List<PersonResponse> build(TravelCalculatePremiumCoreResult result);

}
