package org.travel.insurance.rest.v2;

import org.springframework.stereotype.Component;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import org.travel.insurance.dto.v2.TravelCalculatePremiumResponseV2;

@Component
class TravelCalculatePremiumResponseLoggerV2 {

    private final Logger loggerRequest = LoggerFactory.getLogger(TravelCalculatePremiumResponseLoggerV2.class);
    private final ObjectMapper mapper = new ObjectMapper();

    void run(TravelCalculatePremiumResponseV2 response){
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
