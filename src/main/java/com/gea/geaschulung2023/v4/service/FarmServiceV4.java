package com.gea.geaschulung2023.v4.service;

import com.gea.geaschulung2023.v4.model.FarmV4;
import com.gea.geaschulung2023.v4.repository.FarmRepositoryV4;
import com.gea.geaschulung2023.v4.repository.FarmerRespositoryV4;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FarmServiceV4 {

    private final FarmRepositoryV4 farmRepository;

    private final FarmerRespositoryV4 farmerRepository;

    public FarmServiceV4(FarmRepositoryV4 farmRepository, FarmerRespositoryV4 farmerRepository) {
        this.farmRepository = farmRepository;
        this.farmerRepository = farmerRepository;
    }

    public List<FarmV4> findAllFarms() {
        return farmRepository.findAll();
    }

    public Optional<FarmV4> createFarmAndAssignToFarmer(Long id, FarmV4 farmV4) {
        var farmer = this.farmerRepository.findById(id);
        if (farmer.isEmpty()) {
            return Optional.empty();
        }

        var savedFarm = farmRepository.save(farmV4);

        var farmsOfFarmer = farmer.get().getFarms();
        farmsOfFarmer.add(savedFarm);
        farmerRepository.save(farmer.get());

        return Optional.of(savedFarm);
    }
}
