package com.gea.geaschulung2023.v4.controller;

import com.gea.geaschulung2023.v4.model.FarmerV4;
import com.gea.geaschulung2023.v4.service.FarmerServiceV4;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/v4")
public class FarmerControllerV4 {

    private final FarmerServiceV4 farmerService;

    public FarmerControllerV4(FarmerServiceV4 serviceV4) { this.farmerService = serviceV4; }

    @RequestMapping
    public String index() {
        return "v4";
    }

    @PostMapping("/farmer")
    public ResponseEntity<FarmerV4> createFarmer(@RequestBody FarmerV4 farmer) {
        try {
            var createdFarmer = farmerService.createFarmer(farmer);
            return ResponseEntity.status(HttpStatus.CREATED).body(createdFarmer);
        } catch (DataIntegrityViolationException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @GetMapping("/farmer/{id}")
    public ResponseEntity<FarmerV4> getFarmerById(@PathVariable Long id) {
        var farmer = farmerService.findFarmerById(id);
        return farmer.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/farmer/{id}")
    public ResponseEntity<FarmerV4> updateFarmerById(@PathVariable Long id, @RequestBody FarmerV4 updatedFarmer) {
        var savedUpdatedFarmer = farmerService.updateFarmer(id, updatedFarmer);
        return savedUpdatedFarmer.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/farmer/{id}")
    public ResponseEntity<String> deleteFarmerById(@PathVariable Long id) {
        var isFarmerDeleted = farmerService.deleteFarmer(id);
        return isFarmerDeleted ? ResponseEntity.ok(String.format("Farmer mit der ID %s wurde entfernt", id)) : ResponseEntity.notFound().build();
    }

    @GetMapping("/farmer")
    public ResponseEntity<FarmerV4> getFarmerBySurname(@RequestParam String surname) {
        var farmer = farmerService.findFarmerBySurname(surname);
        return farmer.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/farmers")
    public ResponseEntity<List<FarmerV4>> getFarmersWithinIdIntervall(@RequestParam(required = false) Long from, @RequestParam(required = false) Long to) {
        var farmers = (from != null || to != null) ? farmerService.findFarmersByIds(from, to) : farmerService.findAllFarmers();
        return ResponseEntity.ok(farmers);
    }

}