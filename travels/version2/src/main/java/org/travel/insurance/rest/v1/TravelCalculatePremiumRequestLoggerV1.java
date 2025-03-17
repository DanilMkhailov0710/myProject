package org.travel.insurance.rest.v1;

import org.springframework.stereotype.Component;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import org.travel.insurance.dto.v1.TravelCalculatePremiumRequestV1;

@Component
class TravelCalculatePremiumRequestLoggerV1 {

    private final Logger loggerRequest = LoggerFactory.getLogger(TravelCalculatePremiumRequestLoggerV1.class);
    private final ObjectMapper mapper = new ObjectMapper();

    void run(TravelCalculatePremiumRequestV1 request){
        try {
            mapper.registerModule(new JavaTimeModule());
            String jsonValue = mapper.writeValueAsString(request);
            loggerRequest.info("Request: {}", jsonValue);
        }
        catch (JsonProcessingException e){
            loggerRequest.error("JsonProcessingException was caught", e);
        }
    }

}
