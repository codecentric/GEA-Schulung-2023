package com.gea.geaschulung2023.v1;

import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping(path = "/v1")
public class ControllerV1 {

    @RequestMapping
    public String index() {
        return "v1";
    }

    /// Part 1: Basic Methods

    @GetMapping("/part1")
    public String indexViaGet() {
        return "You requested via GET";
    }

    @PutMapping("/part1")
    public String indexViaPut() {
        return "You requested via PUT";
    }

    @PostMapping("/part1")
    public String indexViaPost() {
        return "You requested via POST";
    }

    @DeleteMapping("/part1")
    public String indexViaDelete() {
        return "You requested via DELETE";
    }

    /// Part 2: Content-Type

    @RequestMapping(path = "/part2", produces = "text/plain")
    public String plainData() {
        return "Hello plain World!";
    }

    @RequestMapping(path = "/part2", consumes = "text/html", produces = "text/html")
    public String htmlData() {
        return "<h1>Hello HTML World!</h1>";
    }

    @RequestMapping(path = "/part2", consumes = "application/json", produces = "application/json")
    public Map<String, String> jsonData() {
        return Map.of("Hello", "World");
    }

    /// Part 3: Headers

    @GetMapping("/part3")
    public String greetOnUserAgent(@RequestHeader(HttpHeaders.USER_AGENT) String userAgent) {
        return String.format("You requested via %s!", userAgent);
    }

    @PostMapping("/part3")
    public String greetOnCustomHeader(@RequestHeader("Custom-Header") String customHeader) {
        return String.format("You requested and sent a custom header: %s", customHeader);
    }

    /// Part 4: Request Variables

    @GetMapping("/part4")
    public String hello(@RequestParam String name) {
        if (name == null) {
            return "Please provide a name";
        }

        return String.format("Hello %s!", name);
    }

    @GetMapping("/{region}/part5")
    public String helloWithPathVariable(@PathVariable String region, @RequestParam String name) {
        if (name == null) {
            return "Please provide a name";
        }

        if (region == null) {
            return "Please provide a valid region";
        }

        if (region.equalsIgnoreCase("de-DE")) {
            return String.format("Hallo %s!", name);
        }

        return String.format("Hello %s!", name);
    }

    /// Part 5: Response Status

    @GetMapping("/part6")
    public ResponseEntity<String> helloWithResponseCodes(@RequestParam(required = false) String name) {
        if (name == null) {
            return ResponseEntity.badRequest().body("Please provide a name");
        } else if (name.equals("Matt")) {
            return ResponseEntity.status(401).body("You're not allowed!");
        }

        return ResponseEntity.ok(String.format("Hello %s!", name));
    }
}
