package org.travel.insurance.dto.v1.adapter;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import org.travel.insurance.core.services.TravelCalculatePremiumService;

import org.travel.insurance.dto.v1.AdapterVersion1;
import org.travel.insurance.dto.v1.TravelCalculatePremiumRequestV1;
import org.travel.insurance.dto.v1.TravelCalculatePremiumResponseV1;

@Component
@RequiredArgsConstructor
class AdapterVersion1Impl implements AdapterVersion1 {

    private final ConverterDtoV1 converter;
    private final TravelCalculatePremiumService service;

    @Override
    public TravelCalculatePremiumResponseV1 calculate(TravelCalculatePremiumRequestV1 request) {
        var command = converter.buildCoreCommand(request);
        var result = service.calculatePremium(command);
        var response = converter.buildResponse(result);

        return response;
    }

}
