package com.gea.geaschulung2023.v5.repository;

import com.gea.geaschulung2023.v5.model.FarmerV5;
import org.springframework.data.repository.Repository;

import java.util.List;
import java.util.Optional;

public interface FarmerRespositoryV5 extends Repository<FarmerV5, Long> {

    FarmerV5 save(FarmerV5 farmer);

    Optional<FarmerV5> findBySurname(String surname);

    Optional<FarmerV5> findById(Long id);

    List<FarmerV5> findByIdBetween(Long from, Long to);

    void deleteById(Long id);

    boolean existsById(Long id);

    List<FarmerV5> findAll();

    int countByNameAndSurname(String name, String surname);
}