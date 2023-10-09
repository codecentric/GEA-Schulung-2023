package com.gea.geaschulung2023.v4.controller;

import com.gea.geaschulung2023.v4.model.FarmerV4;
import com.gea.geaschulung2023.v4.service.FarmServiceV4;
import com.gea.geaschulung2023.v4.service.FarmerServiceV4;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
class FarmerControllerV4Test {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private FarmerServiceV4 farmerService;

    @Test
    void callingPostFarmerEndpointWithValidFarmerRequestShouldReturnStatusCreated() throws Exception {
        String validFarmerJson = """
                {
                  "name": "Bob",
                  "surname": "Dylan",
                  "farms": [
                    {
                      "name": "Stonewall Farm",
                      "numberAnimals": 42,
                      "address": {
                        "street": "242 Chesterfield Rd",
                        "postcode": "03431",
                        "town": "Keene"
                      }
                    }
                  ]
                }""";

        when(farmerService.createFarmer(any(FarmerV4.class))).thenReturn(FarmerV4.exampleFarmer());

        mockMvc.perform(post("/v4/farmer")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(validFarmerJson))
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));

    }
}