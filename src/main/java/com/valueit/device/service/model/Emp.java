package com.valueit.device.service.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@NoArgsConstructor
@Data
@DiscriminatorValue(value = "Empl")


public class Emp extends User  {

//    private String name;
    private Double salary;
    private String fonction;

    public Emp(String username, String password, List<Role> roles, boolean enabled, boolean accountNonExpired, boolean credentialsNonExpired, boolean accountNonLocked, String fonction, Double salary) {
        super(username, password, roles, enabled, accountNonExpired, credentialsNonExpired, accountNonLocked);
        this.fonction = fonction;
        this.salary = salary;
    }

    @OneToOne(cascade=CascadeType.ALL,fetch = FetchType.LAZY)
    @JoinColumn(name = "entreprise")
    private Entreprise entreprise;


    public String getNomEntreprise() {
        return entreprise.getNom();
    }
}
