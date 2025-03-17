package org.travel.insurance.dto.v1.adapter;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import org.travel.insurance.core.api.command.TravelCalculatePremiumCoreCommand;
import org.travel.insurance.core.api.command.TravelCalculatePremiumCoreResult;

import org.travel.insurance.dto.v1.TravelCalculatePremiumRequestV1;
import org.travel.insurance.dto.v1.TravelCalculatePremiumResponseV1;

@Component
@RequiredArgsConstructor
class ConverterDtoV1 {

    private final BuilderAgreementV1 builderAgreement;
    private final BuilderSuccessfullyResponseV1 builderSuccessfullyResponse;
    private final BuilderFailedResponseV1 builderFailedResponse;

    public TravelCalculatePremiumCoreCommand buildCoreCommand(TravelCalculatePremiumRequestV1 request) {
        var agreement = builderAgreement.build(request);

        return new TravelCalculatePremiumCoreCommand(agreement);
    }

    public TravelCalculatePremiumResponseV1 buildResponse(TravelCalculatePremiumCoreResult coreResult) {
        return (coreResult.hasErrors())
                ? builderFailedResponse.build(coreResult)
                : builderSuccessfullyResponse.build(coreResult);
    }

}
