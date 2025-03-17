package org.travel.insurance.core.services;

import org.travel.insurance.core.api.command.TravelCalculatePremiumCoreCommand;
import org.travel.insurance.core.api.command.TravelCalculatePremiumCoreResult;

public interface TravelCalculatePremiumService {

    TravelCalculatePremiumCoreResult calculatePremium(TravelCalculatePremiumCoreCommand command);

}
