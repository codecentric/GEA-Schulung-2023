package com.gea.geaschulung2023.v4.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Setter
@Getter
public class FarmerV4 {

    @GeneratedValue @Id private Long id;

    private String name, surname;

    @OneToMany(cascade = CascadeType.ALL)
    private Set<FarmV4> farms = new HashSet<FarmV4>();

    public static FarmerV4 exampleFarmer() {
        FarmerV4 farmer = new FarmerV4();
        farmer.setName("John");
        farmer.setSurname("Test");

        Set<FarmV4> farms = new HashSet<FarmV4>();

        AddressV4 addressV4 = new AddressV4();
        addressV4.setStreet("Testing Street");
        addressV4.setPostcode("12345");
        addressV4.setTown("Testing Town");

        FarmV4 farm = new FarmV4();
        farm.setName("Testing Farm");
        farm.setNumberAnimals(42);
        farm.setAddress(addressV4);

        farms.add(farm);
        farmer.setFarms(farms);
        return farmer;
    }

}
