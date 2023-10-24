package com.valueit.device.service.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@NoArgsConstructor
@Data
@DiscriminatorValue(value = "Empl")


public class Emp extends User  {

    @Column
    private Double salary;
    private String fonction;


    @ManyToOne//(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "entreprise")
    @JsonBackReference
    private Entreprise entreprise;

//    @OneToOne(cascade=CascadeType.ALL,fetch = FetchType.LAZY)
//    @JoinColumn(name = "entreprise")
//    private Entreprise entreprise;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "emp", cascade = {CascadeType.ALL})
    @JsonManagedReference
    //@PrimaryKeyJoinColumn
    private List<Device> devices;

    public Emp(String username, String password, List<Role> roles, boolean enabled, boolean accountNonExpired, boolean credentialsNonExpired, boolean accountNonLocked, String fonction, Double salary) {
        super(username, password, roles, enabled, accountNonExpired, credentialsNonExpired, accountNonLocked);
        this.fonction = fonction;
        this.salary = salary;
    }

    public String getNomEntreprise() {
        return entreprise.getNom();
    }
}
