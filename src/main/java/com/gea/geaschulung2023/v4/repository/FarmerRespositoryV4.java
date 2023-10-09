package com.gea.geaschulung2023.v4.repository;

import com.gea.geaschulung2023.v4.model.FarmerV4;
import org.springframework.data.repository.Repository;

import java.util.List;
import java.util.Optional;

public interface FarmerRespositoryV4 extends Repository<FarmerV4, Long> {

    FarmerV4 save(FarmerV4 farmer);

    Optional<FarmerV4> findBySurname(String surname);

    Optional<FarmerV4> findById(Long id);

    List<FarmerV4> findByIdBetween(Long from, Long to);

    void deleteById(Long id);

    boolean existsById(Long id);

    List<FarmerV4> findAll();

    boolean existsByNameAndSurname(String name, String surname);

    int countByNameAndSurname(String name, String surname);
}