package com.gea.geaschulung2023.v3;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class FarmerServiceV3 {

    private final FarmerRespositoryV3 farmerRespository;

    public FarmerServiceV3(FarmerRespositoryV3 farmerRespository) {
        this.farmerRespository = farmerRespository;
    }

    public FarmerV3 createFarmer(FarmerV3 farmer) {
        return farmerRespository.save(farmer);
    }

    public Optional<FarmerV3> findFarmerBySurname(String surname) {
        return farmerRespository.findBySurname(surname);
    }

    public List<FarmerV3> findFarmersByIds(Long from, Long to) {
        if (from < 0 || from > to) {
            return new ArrayList<>();
        }
        return farmerRespository.findByIdBetween(from, to);
    }

    public Optional<FarmerV3> findFarmerById(Long id) {
        return farmerRespository.findById(id);
    }

    public boolean deleteFarmer(Long id) {
        var isFarmerExisting = farmerRespository.existsById(id);
        if (isFarmerExisting) {
            farmerRespository.deleteById(id);
            return true;
        } else {
            return false;
        }

    }

    public Optional<FarmerV3> updateFarmer(Long id, FarmerV3 updatedFarmer) {
        var isFarmerExisting = farmerRespository.existsById(id);
        if (isFarmerExisting) {
            updatedFarmer.setId(id);
            return Optional.of(farmerRespository.save(updatedFarmer));
        } else {
            return Optional.empty();
        }
    }
}
