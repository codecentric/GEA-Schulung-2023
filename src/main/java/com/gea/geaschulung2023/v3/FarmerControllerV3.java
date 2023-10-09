package com.gea.geaschulung2023.v3;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/v3")
public class FarmerControllerV3 {

    private final FarmerServiceV3 farmerService;

    public FarmerControllerV3(FarmerServiceV3 farmerService) { this.farmerService = farmerService; }

    @RequestMapping
    public String index() {
        return "v3";
    }

    @PostMapping("/farmer")
    public ResponseEntity<FarmerV3> createFarmer(@RequestBody FarmerV3 farmer) {
        var createdFarmer = farmerService.createFarmer(farmer);
        return ResponseEntity.ok(createdFarmer);
    }

    @GetMapping("/farmer/{id}")
    public ResponseEntity<FarmerV3> getFarmerById(@PathVariable Long id) {
        var farmer = farmerService.findFarmerById(id);
        return farmer.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/farmer/{id}")
    public ResponseEntity<FarmerV3> updateFarmerById(@PathVariable Long id, @RequestBody FarmerV3 updatedFarmer) {
        var savedUpdatedFarmer = farmerService.updateFarmer(id, updatedFarmer);
        return savedUpdatedFarmer.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/farmer/{id}")
    public ResponseEntity<String> deleteFarmerById(@PathVariable Long id) {
        var isFarmerDeleted = farmerService.deleteFarmer(id);
        return isFarmerDeleted ? ResponseEntity.ok(String.format("Farmer mit der ID %s wurde entfernt", id)) : ResponseEntity.notFound().build();
    }

    @GetMapping("/farmer")
    public ResponseEntity<FarmerV3> getFarmerBySurname(@RequestParam String surname) {
        var farmer = farmerService.findFarmerBySurname(surname);
        return farmer.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/farmers")
    public ResponseEntity<List<FarmerV3>> getFarmersWithinIdIntervall(@RequestParam Long from, @RequestParam Long to) {
        var farmers = farmerService.findFarmersByIds(from, to);
        return ResponseEntity.ok(farmers);
    }

}