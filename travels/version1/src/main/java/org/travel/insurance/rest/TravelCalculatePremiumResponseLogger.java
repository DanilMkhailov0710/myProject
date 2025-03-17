package org.travel.insurance.rest;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.travel.insurance.dto.v1.TravelCalculatePremiumResponseV1;

@Component
class TravelCalculatePremiumResponseLogger {

    private final Logger loggerRequest = LoggerFactory.getLogger(TravelCalculatePremiumRequestLogger.class);
    private final ObjectMapper mapper = new ObjectMapper();

    void run(TravelCalculatePremiumResponseV1 response){
        try {
            mapper.registerModule(new JavaTimeModule());
            String jsonValue = mapper.writeValueAsString(response);
            loggerRequest.info("Response: {}", jsonValue);
        }
        catch (JsonProcessingException e){
            loggerRequest.error("JsonProcessingException was caught", e);
        }
    }

}
