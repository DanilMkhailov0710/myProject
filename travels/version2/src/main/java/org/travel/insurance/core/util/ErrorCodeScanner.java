package org.travel.insurance.core.util;

import org.springframework.stereotype.Component;

import org.springframework.core.io.Resource;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.support.PropertiesLoaderUtils;

import java.util.List;
import java.io.IOException;
import java.util.Properties;

@Component
public class ErrorCodeScanner {

    private final Properties properties;

    ErrorCodeScanner() {
        try {
            Resource resource = new ClassPathResource("errorCodes.properties");
            properties = PropertiesLoaderUtils.loadProperties(resource);
        }
        catch (IOException e){
            throw new RuntimeException(e);
        }
    }

    public String getErrorDescription(String errorCode) {
        return properties.getProperty(errorCode);
    }

    public String getErrorDescription(String errorCode, List<Placeholder> placeholders) {
        String errorDescription = properties.getProperty(errorCode);
        for(Placeholder placeholder : placeholders) {
            String placeholderToReplace = "{" + placeholder.getPlaceholderName() + "}";
            errorDescription = errorDescription.replace(placeholderToReplace, placeholder.getPlaceholderValue());
        }
        return errorDescription;
    }

}