package com.gea.geaschulung2023.v5.controller;

import com.gea.geaschulung2023.v5.service.FarmerServiceV5;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
class FarmerControllerV5Test {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private FarmerServiceV5 farmerService;

    @Autowired
    PasswordEncoder encoder;

    @Test
    public void testPublicEndpointAccessibleWithoutAuthentication() throws Exception {
        mockMvc.perform(get("/v5/farmers").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void testProtectedEndpointNotAccessibleWithoutAuthentication() throws Exception {
        mockMvc.perform(get("/v5/farms").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isUnauthorized());
    }

    @Test
    @WithMockUser(username="farm.manager", authorities = "READ_PRIVILEGES")
    public void testProtectedEndpointAccessibleWithAuthorityAndAuthentication() throws Exception {
        mockMvc.perform(get("/v5/farms").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser(username="farm.manager", authorities = "NO_PRIVILEGES")
    public void testProtectedEndpointNotAccessibleWithMissingAuthorityAndAuthentication() throws Exception {
        mockMvc.perform(get("/v5/farms").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isForbidden());
    }

    @Test
    @WithMockUser(username="farm.manager", roles="FARM_MANAGER")
    public void testProtectedEndpointAccessibleWithRoleAndAuthentication() throws Exception {
        when(farmerService.deleteFarmer(anyLong())).thenReturn(true);

        mockMvc.perform(delete("/v5/farmer/1").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser(username="farm.manager", roles="GUEST")
    public void testProtectedEndpointNotAccessibleWithMissingRoleAndAuthentication() throws Exception {
        when(farmerService.deleteFarmer(anyLong())).thenReturn(true);

        mockMvc.perform(delete("/v5/farmer/1").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isForbidden());
    }

}