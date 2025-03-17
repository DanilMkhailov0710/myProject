package org.travel.insurance.rest;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.google.common.base.Stopwatch;
import org.travel.insurance.core.service.TravelCalculatePremiumService;
import org.travel.insurance.dto.v1.TravelCalculatePremiumRequestV1;
import org.travel.insurance.dto.v1.TravelCalculatePremiumResponseV1;

@RequiredArgsConstructor(access = AccessLevel.PACKAGE)
@RestController
@RequestMapping("/insurance/travel/api")
class TravelCalculatePremiumController {

	private final TravelCalculatePremiumService calculatePremiumService;

	private final TravelCalculatePremiumRequestLogger loggerRequest;
	private final TravelCalculatePremiumResponseLogger loggerResponse;
	private final TravelCalculatePremiumComputeDurationRequest calculator;

	@PostMapping(path = "/",
			consumes = "application/json",
			produces = "application/json")
	public TravelCalculatePremiumResponseV1 calculatePremium(@RequestBody TravelCalculatePremiumRequestV1 request) {
		loggerRequest.run(request);

		Stopwatch stopwatch = Stopwatch.createStarted();
		var response = calculatePremiumService.calculatePremium(request);
		stopwatch.stop();

		loggerResponse.run(response);
		calculator.run(stopwatch);

		return response;
	}

}