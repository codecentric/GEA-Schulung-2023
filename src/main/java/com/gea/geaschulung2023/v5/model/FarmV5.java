package com.gea.geaschulung2023.v5.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Setter
@Getter
public class FarmV5 {

    @GeneratedValue @Id private Long id;

    private String name;

    private int numberAnimals;

    @OneToOne(cascade = CascadeType.ALL)
    private AddressV5 address;

}
