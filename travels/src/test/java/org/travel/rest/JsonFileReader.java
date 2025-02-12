package org.travel.rest;

import org.springframework.stereotype.Component;
import org.springframework.util.ResourceUtils;

import java.io.*;
import java.nio.file.Files;

@Component
public class JsonFileReader {

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

    public static void main(String[] args) {
        JsonFileReader reader = new JsonFileReader();
        System.out.println(reader.readJsonFromFile("rest\\TravelCalculatePremiumRequest1.json"));
    }
}

