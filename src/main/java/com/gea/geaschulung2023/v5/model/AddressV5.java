package com.gea.geaschulung2023.v5.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class AddressV5 {

    @GeneratedValue @Id private Long id;

    String street;

    String postcode;

    String town;

}
