package com.gea.geaschulung2023.v2;

import org.springframework.stereotype.Service;

@Service
public class ServiceV2 {

    public String greet(String name) {
        return String.format("Hello %s, how are you today?", name);
    }
}
