package com.gea.geaschulung2023.v5.repository;

import com.gea.geaschulung2023.v5.model.FarmV5;
import org.springframework.data.repository.Repository;

import java.util.List;

public interface FarmRepositoryV5 extends Repository<FarmV5, Long> {

    List<FarmV5> findAll();

    FarmV5 save(FarmV5 farmV4);

}
