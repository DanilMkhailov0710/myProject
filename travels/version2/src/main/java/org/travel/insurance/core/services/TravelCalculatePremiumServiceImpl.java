package org.travel.insurance.core.services;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import org.travel.insurance.core.api.command.TravelCalculatePremiumCoreCommand;
import org.travel.insurance.core.api.command.TravelCalculatePremiumCoreResult;

import org.travel.insurance.core.validations.TravelAgreementValidator;

import org.travel.insurance.core.api.dto.AgreementDTO;
import org.travel.insurance.core.api.dto.ValidationErrorDTO;

import java.util.List;
import java.math.BigDecimal;

@Component
@RequiredArgsConstructor(access = AccessLevel.PACKAGE)
class TravelCalculatePremiumServiceImpl implements TravelCalculatePremiumService {

    private final TravelAgreementValidator agreementValidator;
    private final CalculatorForEachPersons calculatorPersons;
    private final CalculatorTotalPrice calculatorTotalPrice;

    @Override
    public TravelCalculatePremiumCoreResult calculatePremium(TravelCalculatePremiumCoreCommand command) {
        List<ValidationErrorDTO> errors = agreementValidator.validate(command.getAgreement());
        return errors.isEmpty()
                ? buildResponse(command.getAgreement())
                : buildResponse(errors);
    }

    private TravelCalculatePremiumCoreResult buildResponse(List<ValidationErrorDTO> errors) {
        return new TravelCalculatePremiumCoreResult(errors);
    }

    private TravelCalculatePremiumCoreResult buildResponse(AgreementDTO agreement) {
        calculatorPersons.calculateRiskPremiumsForAllPersons(agreement);

        BigDecimal totalAgreementPremium = calculatorTotalPrice.calculateTotalAgreementPremium(agreement);
        agreement.setAgreementPremium(totalAgreementPremium);

        TravelCalculatePremiumCoreResult coreResult = new TravelCalculatePremiumCoreResult();
        coreResult.setAgreement(agreement);
        return coreResult;
    }

}
