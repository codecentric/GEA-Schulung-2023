package com.gea.geaschulung2023.v5.service;

import com.gea.geaschulung2023.v4.model.FarmerV4;
import com.gea.geaschulung2023.v5.model.FarmerV5;
import com.gea.geaschulung2023.v5.repository.FarmerRespositoryV5;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class FarmerServiceV5 {

    private final FarmerRespositoryV5 farmerRespository;

    public FarmerServiceV5(FarmerRespositoryV5 farmerRespository) {
        this.farmerRespository = farmerRespository;
    }

    @Transactional
    public FarmerV5 createFarmer(FarmerV5 farmer) throws DataIntegrityViolationException {
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

    public Optional<FarmerV5> findFarmerBySurname(String surname) {
        return farmerRespository.findBySurname(surname);
    }

    public List<FarmerV5> findFarmersByIds(Long from, Long to) {
        return farmerRespository.findByIdBetween(from, to);
    }

    public Optional<FarmerV5> findFarmerById(Long id) {
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

    public Optional<FarmerV5> updateFarmer(Long id, FarmerV5 updatedFarmer) {
        var isFarmerExisting = farmerRespository.existsById(id);
        if (isFarmerExisting) {
            updatedFarmer.setId(id);
            return Optional.of(farmerRespository.save(updatedFarmer));
        } else {
            return Optional.empty();
        }
    }

    public List<FarmerV5> findAllFarmers() {
        return farmerRespository.findAll();
    }

}
