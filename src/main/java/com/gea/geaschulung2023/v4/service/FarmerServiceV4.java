package com.gea.geaschulung2023.v4.service;

import com.gea.geaschulung2023.v4.model.FarmerV4;
import com.gea.geaschulung2023.v4.repository.FarmerRespositoryV4;
import com.gea.geaschulung2023.v5.model.FarmerV5;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import java.util.List;
import java.util.Optional;

@Service
public class FarmerServiceV4 {

    private final FarmerRespositoryV4 farmerRespository;

    public FarmerServiceV4(FarmerRespositoryV4 farmerRespository) {
        this.farmerRespository = farmerRespository;
    }

    @Transactional
    public FarmerV4 createFarmer(FarmerV4 farmer) throws DataIntegrityViolationException {
        if (farmer.getName() == null || farmer.getSurname() == null) {
            return null;
        }

        var createdFarmer = farmerRespository.save(farmer);

        var numberFarmerWithGivenName = this.farmerRespository.countByNameAndSurname(farmer.getName(), farmer.getSurname());

        if (numberFarmerWithGivenName > 1) {
            throw new DataIntegrityViolationException("Farmer already exists. Rollback!");
        }

        return createdFarmer;
    }

    public Optional<FarmerV4> findFarmerBySurname(String surname) {
        return farmerRespository.findBySurname(surname);
    }

    public List<FarmerV4> findFarmersByIds(Long from, Long to) {
        return farmerRespository.findByIdBetween(from, to);
    }

    public Optional<FarmerV4> findFarmerById(Long id) {
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

    public Optional<FarmerV4> updateFarmer(Long id, FarmerV4 updatedFarmer) {
        var isFarmerExisting = farmerRespository.existsById(id);
        if (isFarmerExisting) {
            updatedFarmer.setId(id);
            return Optional.of(farmerRespository.save(updatedFarmer));
        } else {
            return Optional.empty();
        }
    }

    public List<FarmerV4> findAllFarmers() {
        return farmerRespository.findAll();
    }

}
