package org.travel.insurance.rest.v1;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.google.common.base.Stopwatch;

import org.travel.insurance.dto.v1.AdapterVersion1;
import org.travel.insurance.dto.v1.TravelCalculatePremiumRequestV1;
import org.travel.insurance.dto.v1.TravelCalculatePremiumResponseV1;

@RequiredArgsConstructor(access = AccessLevel.PACKAGE)
@RestController
@RequestMapping("/insurance/travel/api/v1")
class TravelCalculatePremiumControllerV1 {

	private final AdapterVersion1 adapter;

	private final TravelCalculatePremiumRequestLoggerV1 loggerRequest;
	private final TravelCalculatePremiumResponseLoggerV1 loggerResponse;
	private final TravelCalculatePremiumComputeDurationRequestV1 calculator;

	@PostMapping(path = "/",
			consumes = "application/json",
			produces = "application/json")
	public TravelCalculatePremiumResponseV1 calculatePremium(@RequestBody TravelCalculatePremiumRequestV1 request) {
		loggerRequest.run(request);

		Stopwatch stopwatch = Stopwatch.createStarted();
		var response = adapter.calculate(request);
		stopwatch.stop();

		loggerResponse.run(response);
		calculator.run(stopwatch);

		return response;
	}

}