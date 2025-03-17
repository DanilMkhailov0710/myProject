package org.travel.insurance.dto.v2.adapter;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import org.travel.insurance.core.services.TravelCalculatePremiumService;

import org.travel.insurance.dto.v2.AdapterVersion2;
import org.travel.insurance.dto.v2.TravelCalculatePremiumRequestV2;
import org.travel.insurance.dto.v2.TravelCalculatePremiumResponseV2;

@Component
@RequiredArgsConstructor
class AdapterVersion2Impl implements AdapterVersion2 {

    private final ConverterDtoV2 converter;
    private final TravelCalculatePremiumService service;

    @Override
    public TravelCalculatePremiumResponseV2 calculate(TravelCalculatePremiumRequestV2 request) {
        var command = converter.buildCoreCommand(request);
        var result = service.calculatePremium(command);
        var response = converter.buildResponse(result);

        return response;
    }

}
