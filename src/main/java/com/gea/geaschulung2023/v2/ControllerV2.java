package com.gea.geaschulung2023.v2;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/v2")
public class ControllerV2 {

    private final ServiceV2 serviceV2;

    public ControllerV2(ServiceV2 serviceV2) {
        this.serviceV2 = serviceV2;
    }

    @RequestMapping
    public String index() {
        return "v2";
    }

    /// Part 1: Autowiring Components

    @GetMapping("/part1")
    public ResponseEntity<String> greetViaService(@RequestParam String name) {
        var greeting = serviceV2.greet(name);
        return ResponseEntity.ok(greeting);
    }
}