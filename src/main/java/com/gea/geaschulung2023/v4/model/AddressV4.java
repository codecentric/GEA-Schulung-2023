package com.gea.geaschulung2023.v4.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class AddressV4 {

    @GeneratedValue @Id private Long id;

    String street;

    String postcode;

    String town;

}