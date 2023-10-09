package com.gea.geaschulung2023.v5.controller;

import com.gea.geaschulung2023.v5.model.FarmV5;
import com.gea.geaschulung2023.v5.service.FarmServiceV5;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/v5")
public class FarmControllerV5 {

    private final FarmServiceV5 farmService;

    public FarmControllerV5(FarmServiceV5 farmService) { this.farmService = farmService; }

    @PostMapping("/farmer/{id}/farm")
    public ResponseEntity<FarmV5> createFarm(@PathVariable Long id, @RequestBody FarmV5 farmV5) {
        var createdFarm = farmService.createFarmAndAssignToFarmer(id, farmV5);
        return createdFarm.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/farms")
    public ResponseEntity<List<FarmV5>> findAllFarms() {
        var farms = farmService.findAllFarms();
        return ResponseEntity.ok(farms);
    }

}
