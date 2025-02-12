package org.travel.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
public class TravelCalculatePremiumControllerTest {

    @Autowired private MockMvc mockMvc;
    @Autowired private JsonFileReader readerJson;

    private final ObjectMapper mapper = new ObjectMapper();


    @Test
    @DisplayName("Test case 1")
    public void simpleRestControllerTestFirstName() throws Exception {
        templateCodeTest(1);
    }

    @Test
    @DisplayName("Test case 2")
    public void simpleRestControllerTestLastName() throws Exception {
        templateCodeTest(2);
    }

    @Test
    @DisplayName("Test case 3")
    public void simpleRestControllerTestDateFrom() throws Exception {
        templateCodeTest(3);
    }

    @Test
    @DisplayName("Test case 4")
    public void simpleRestControllerTestDateTo() throws Exception {
        templateCodeTest(4);
    }

    @Test
    @DisplayName("Test case 5")
    public void simpleRestControllerAll() throws Exception {
        templateCodeTest(5);
    }

    @Test
    @DisplayName("Test case 6")
    public void simpleRestControllerDates() throws Exception {
        templateCodeTest(6);
    }

    @Test
    @DisplayName("Test case 7")
    public void simpleRestControllerTestNotSuccess() throws Exception {
        templateCodeTest(7);
    }

    @Test
    @DisplayName("Test case 8")
    public void simpleRestControllerTestSuccess() throws Exception {
        templateCodeTest(8);
    }

    private void templateCodeTest(int n) throws Exception {
        String path = String.format(
                "rest/TravelCalculatePremiumRequest%s.json", n);
        String pathResponse = String.format(
                "rest/TravelCalculatePremiumResponse%s.json", n);

        templateCode(path, pathResponse);
    }

    private void templateCode(String pathRequest, String pathResponse) throws Exception {

        var responseFromController = mockMvc.perform(post("/insurance/travel/")
                        .content(readerJson.readJsonFromFile(pathRequest))
                        .header(HttpHeaders.CONTENT_TYPE,
                                MediaType.APPLICATION_JSON_VALUE)
                ).andExpect(status().isOk())
                .andExpect(content().json(readerJson.readJsonFromFile(pathResponse)));
    }
}