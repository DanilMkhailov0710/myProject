package org.travel.insurance.rest.v2;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.google.common.base.Stopwatch;

import org.travel.insurance.dto.v2.AdapterVersion2;
import org.travel.insurance.dto.v2.TravelCalculatePremiumRequestV2;
import org.travel.insurance.dto.v2.TravelCalculatePremiumResponseV2;

@RequiredArgsConstructor(access = AccessLevel.PACKAGE)
@RestController
@RequestMapping("/insurance/travel/api/v2")
class TravelCalculatePremiumControllerV2 {

	private final AdapterVersion2 adapter;

	private final TravelCalculatePremiumRequestLoggerV2 loggerRequest;
	private final TravelCalculatePremiumResponseLoggerV2 loggerResponse;
	private final TravelCalculatePremiumComputeDurationRequestV2 calculator;

	@PostMapping(path = "/",
			consumes = "application/json",
			produces = "application/json")
	public TravelCalculatePremiumResponseV2 calculatePremium(@RequestBody TravelCalculatePremiumRequestV2 request) {
		loggerRequest.run(request);

		Stopwatch stopwatch = Stopwatch.createStarted();
		var response = adapter.calculate(request);
		stopwatch.stop();

		loggerResponse.run(response);
		calculator.run(stopwatch);

		return response;
	}

}