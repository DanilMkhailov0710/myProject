package org.travel.insurance.dto.v2.adapter.converter_persons;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import org.travel.insurance.dto.v2.PersonResponse;
import org.travel.insurance.core.api.dto.PersonDTO;
import org.travel.insurance.dto.v2.TravelCalculatePremiumRequestV2;
import org.travel.insurance.core.api.command.TravelCalculatePremiumCoreResult;

import java.util.List;

@Component
@RequiredArgsConstructor
class ConverterPersonsDTOV2Impl implements ConverterPersonsDTOV2{

    private final FactoryPersonDTOV2 builderPersonDTO;
    private final FactoryPersonResponseV2 builderPersonResponse;

    public List<PersonDTO> build(TravelCalculatePremiumRequestV2 request) {
        return request.getPersons()
                .stream()
                .map(builderPersonDTO::build)
                .toList();
    }

    public List<PersonResponse> build(TravelCalculatePremiumCoreResult result) {
        return result.getAgreement().getPersons()
                .stream()
                .map(builderPersonResponse::build)
                .toList();
    }

}
