package com.gea.geaschulung2023.v4.endtoend;

import com.gea.geaschulung2023.v4.model.FarmerV4;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
public class IntegrationTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void postFarmerEndpoint() {
        FarmerV4 farmerRequest = FarmerV4.exampleFarmer();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        ResponseEntity<FarmerV4> response = restTemplate.postForEntity("/v4/farmer", new HttpEntity<>(farmerRequest, headers), FarmerV4.class);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertNotNull(response.getBody());
    }
}
