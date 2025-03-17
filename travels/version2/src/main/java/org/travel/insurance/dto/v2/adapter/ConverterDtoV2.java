package org.travel.insurance.dto.v2.adapter;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import org.travel.insurance.core.api.command.TravelCalculatePremiumCoreCommand;
import org.travel.insurance.core.api.command.TravelCalculatePremiumCoreResult;

import org.travel.insurance.dto.v2.TravelCalculatePremiumRequestV2;
import org.travel.insurance.dto.v2.TravelCalculatePremiumResponseV2;

@Component
@RequiredArgsConstructor
class ConverterDtoV2 {

    private final BuilderAgreementV2 builderAgreement;
    private final BuilderSuccessfullyResponseV2 builderSuccess;
    private final BuilderFailedResponseV2 builderFailed;

    public TravelCalculatePremiumCoreCommand buildCoreCommand(TravelCalculatePremiumRequestV2 request) {
        var agreement = builderAgreement.build(request);
        return new TravelCalculatePremiumCoreCommand(agreement);
    }

    public TravelCalculatePremiumResponseV2 buildResponse(TravelCalculatePremiumCoreResult coreResult) {
        return (coreResult.hasErrors())
                ? builderFailed.buildResponse(coreResult)
                : builderSuccess.buildResponse(coreResult);
    }

}
