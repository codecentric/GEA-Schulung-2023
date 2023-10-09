package com.gea.geaschulung2023.v4.repository;

import com.gea.geaschulung2023.v4.model.FarmV4;
import org.springframework.data.repository.Repository;

import java.util.List;

public interface FarmRepositoryV4 extends Repository<FarmV4, Long> {

    List<FarmV4> findAll();

    FarmV4 save(FarmV4 farmV4);

}
