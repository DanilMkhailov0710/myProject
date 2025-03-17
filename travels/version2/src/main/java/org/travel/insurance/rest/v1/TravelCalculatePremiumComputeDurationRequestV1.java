package org.travel.insurance.rest.v1;

import org.springframework.stereotype.Component;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.TimeUnit;

import com.google.common.base.Stopwatch;

@Component
class TravelCalculatePremiumComputeDurationRequestV1 {

    private final Logger loggerRequest = LoggerFactory.getLogger(TravelCalculatePremiumComputeDurationRequestV1.class);

    void run(Stopwatch stopwatch){
        long time = stopwatch.elapsed(TimeUnit.MILLISECONDS);
        loggerRequest.info("Time executing: {} milliseconds", time);
    }
}
