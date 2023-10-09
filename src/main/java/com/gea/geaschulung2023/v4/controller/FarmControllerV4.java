package com.gea.geaschulung2023.v4.controller;

import com.gea.geaschulung2023.v4.model.FarmV4;
import com.gea.geaschulung2023.v4.service.FarmServiceV4;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/v4")
public class FarmControllerV4 {

    private final FarmServiceV4 farmService;

    public FarmControllerV4(FarmServiceV4 farmService) { this.farmService = farmService; }

    @PostMapping("/farmer/{id}/farm")
    public ResponseEntity<FarmV4> createFarm(@PathVariable Long id, @RequestBody FarmV4 farmV4) {
        var createdFarm = farmService.createFarmAndAssignToFarmer(id, farmV4);
        return createdFarm.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/farms")
    public ResponseEntity<List<FarmV4>> findAllFarms() {
        var farms = farmService.findAllFarms();
        return ResponseEntity.ok(farms);
    }

}
