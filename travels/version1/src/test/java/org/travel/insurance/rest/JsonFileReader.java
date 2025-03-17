package org.travel.insurance.rest;

import org.springframework.stereotype.Component;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

@Component
class JsonFileReader {

    public String readJsonFromFile(String filePath){
        try {
            File file = ResourceUtils
                    .getFile("classpath:" + filePath);

            return Files.readAllLines(file.toPath())
                    .stream()
                    .reduce((x, y) -> x + y)
                    .get();
        }
        catch (IOException e){
            throw new RuntimeException(e);
        }
    }

}