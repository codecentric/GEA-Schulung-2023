package com.gea.geaschulung2023.v4.repository;

import com.gea.geaschulung2023.v4.model.FarmerV4;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
class FarmerRespositoryV4Test {

    @Autowired
    private TestEntityManager entityManager;

    @Test
    void shouldStoreAFarmer() {
        FarmerV4 farmer = FarmerV4.exampleFarmer();

        var persistedFarmer = this.entityManager.persist(farmer);

        assertEquals(farmer.getName(), persistedFarmer.getName());
        assertEquals(farmer.getSurname(), persistedFarmer.getSurname());
        assertEquals(farmer.getFarms(), persistedFarmer.getFarms());
    }

}