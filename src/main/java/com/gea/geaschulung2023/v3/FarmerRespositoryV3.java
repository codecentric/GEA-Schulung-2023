package com.gea.geaschulung2023.v3;

import org.springframework.data.repository.Repository;

import java.util.List;
import java.util.Optional;

public interface FarmerRespositoryV3 extends Repository<FarmerV3, Long> {

    FarmerV3 save(FarmerV3 farmer);

    Optional<FarmerV3> findBySurname(String surname);

    Optional<FarmerV3> findById(Long id);

    List<FarmerV3> findByIdBetween(Long from, Long to);

    void deleteById(Long id);

    boolean existsById(Long id);

}