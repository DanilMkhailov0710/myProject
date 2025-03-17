package org.travel.insurance.rest.v2;

import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.springframework.http.MediaType;
import org.springframework.http.HttpHeaders;

import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import org.travel.insurance.rest.common.JsonFileReader;

import java.util.List;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
class TravelCalculatePremiumControllerV2Test {

    @Autowired private MockMvc mockMvc;
    @Autowired private JsonFileReader readerJson;

    private final static String url = "/insurance/travel/api/v2/";

    @ParameterizedTest
    @MethodSource("numberTests")
    public void templateCodeTest(int n) throws Exception {
        String path = String.format(
                "rest/v2/test_case_%s/request.json", n);
        String pathResponse = String.format(
                "rest/v2/test_case_%s/response.json", n);

        templateCode(path, pathResponse);
    }

    private void templateCode(String pathRequest, String pathResponse) throws Exception {
        mockMvc.perform(post(url)
                        .content(readerJson.readJsonFromFile(pathRequest))
                        .header(HttpHeaders.CONTENT_TYPE,
                                MediaType.APPLICATION_JSON_VALUE)
                ).andExpect(status().isOk())
                .andExpect(content().json(readerJson.readJsonFromFile(pathResponse)));
    }

    private static List<Integer> numberTests() {
        return List.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10,
                11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22,
                23, 24, 25, 26, 27, 28, 29, 30, 31);
    }

}