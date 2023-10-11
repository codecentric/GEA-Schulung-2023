package com.gea.geaschulung2023.v4.service;

import com.gea.geaschulung2023.v4.model.FarmerV4;
import com.gea.geaschulung2023.v4.repository.FarmerRespositoryV4;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class FarmerServiceV4Test {

    @Mock
    FarmerRespositoryV4 farmerRepository;

    @InjectMocks
    FarmerServiceV4 farmerService;

    @Test
    void validFarmerRequestShouldCreateFarmer() {
        FarmerV4 farmerRequest = FarmerV4.exampleFarmer();
        FarmerV4 farmerResponse = FarmerV4.exampleFarmer();
        farmerResponse.setId(1L);
        farmerResponse.getFarms().forEach(farm -> farm.setId(1L));

        when(farmerRepository.save(any(FarmerV4.class))).thenReturn(farmerResponse);

        var createdFarmer = farmerService.createFarmer(farmerRequest);

        assertNotNull(createdFarmer);
        assertEquals(1L, createdFarmer.getId());
    }

    @Test
    void invalidFarmerRequestShouldNotCreateFarmerAndReturnNull() {
        FarmerV4 farmerRequest = FarmerV4.exampleFarmer();
        farmerRequest.setName(null);
        farmerRequest.setSurname(null);

        var createdFarmer = farmerService.createFarmer(farmerRequest);

        assertNull(createdFarmer);
    }

    @Test
    void creatingFarmerTwiceShouldNotCreateTwoFarmerAndShouldRollback() {
        FarmerV4 farmerRequest = FarmerV4.exampleFarmer();
        FarmerV4 farmerResponse = FarmerV4.exampleFarmer();
        farmerResponse.setId(1L);
        farmerResponse.getFarms().forEach(farm -> farm.setId(1L));

        when(farmerRepository.save(any(FarmerV4.class))).thenReturn(farmerResponse);

        var createdFarmer = farmerService.createFarmer(farmerRequest);

        when(farmerRepository.countByNameAndSurname(anyString(), anyString())).thenReturn(2);

        assertNotNull(createdFarmer, "First farmer create request should be successful!");
        assertThrowsExactly(DataIntegrityViolationException.class, () -> farmerService.createFarmer(farmerRequest));
    }
}