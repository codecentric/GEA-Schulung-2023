package com.gea.geaschulung2023.v5.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Entity
@Setter
@Getter
public class FarmerV5 {

    @GeneratedValue @Id private Long id;

    private String name, surname;

    @OneToMany(cascade = CascadeType.ALL)
    private Set<FarmV5> farms = new HashSet<>();

    public static FarmerV5 exampleFarmer() {
        FarmerV5 farmer = new FarmerV5();
        farmer.setName("John");
        farmer.setSurname("Test");

        Set<FarmV5> farms = new HashSet<>();

        AddressV5 addressV4 = new AddressV5();
        addressV4.setStreet("Testing Street");
        addressV4.setPostcode("12345");
        addressV4.setTown("Testing Town");

        FarmV5 farm = new FarmV5();
        farm.setName("Testing Farm");
        farm.setNumberAnimals(42);
        farm.setAddress(addressV4);

        farms.add(farm);
        farmer.setFarms(farms);
        return farmer;
    }

}
