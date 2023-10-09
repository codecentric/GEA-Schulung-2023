package com.gea.geaschulung2023.v5.service;

import com.gea.geaschulung2023.v5.model.FarmV5;
import com.gea.geaschulung2023.v5.repository.FarmRepositoryV5;
import com.gea.geaschulung2023.v5.repository.FarmerRespositoryV5;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FarmServiceV5 {

    private final FarmRepositoryV5 farmRepository;

    private final FarmerRespositoryV5 farmerRepository;

    public FarmServiceV5(FarmRepositoryV5 farmRepository, FarmerRespositoryV5 farmerRepository) {
        this.farmRepository = farmRepository;
        this.farmerRepository = farmerRepository;
    }

    public List<FarmV5> findAllFarms() {
        return farmRepository.findAll();
    }

    public Optional<FarmV5> createFarmAndAssignToFarmer(Long id, FarmV5 farmV5) {
        var farmer = this.farmerRepository.findById(id);
        if (farmer.isEmpty()) {
            return Optional.empty();
        }

        var savedFarm = farmRepository.save(farmV5);

        var farmsOfFarmer = farmer.get().getFarms();
        farmsOfFarmer.add(savedFarm);
        farmerRepository.save(farmer.get());

        return Optional.of(savedFarm);
    }
}
