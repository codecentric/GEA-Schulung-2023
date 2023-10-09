package com.gea.geaschulung2023.v3;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class FarmerV3 {

    private @GeneratedValue @Id Long id;

    private String name, surname;

}
