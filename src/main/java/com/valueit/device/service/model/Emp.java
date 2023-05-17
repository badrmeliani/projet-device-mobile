package com.valueit.device.service.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@NoArgsConstructor
@Data
@DiscriminatorValue(value = "USER")
public class Emp extends User  {
    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private Double salary;
    private String fonction;

    public Emp(String name, Double salary, String fonction) {
        super();
        this.name = name;
        this.salary = salary;
        this.fonction = fonction;
    }

}
