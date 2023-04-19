package com.valueit.device.service.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@NoArgsConstructor
@Data

public class Entreprise {
    @Id
    @GeneratedValue
    @Column(name = "id_entreprise")
    private Long id;
    private String nom;
    private String adresse;
    private int capitale;
    private String fondateur;
    @Transient
    private Date date_creation;

    public Entreprise(Long id, String nom, String adresse, int capitale, String fondateur, Date dateCreation) {
        this.nom = nom;
        this.adresse = adresse;
        this.capitale = capitale;
        this.fondateur = fondateur;
    }

    public Entreprise(String nom) {
        this.nom = nom;
    }

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "entreprise",cascade = {CascadeType.MERGE,CascadeType.PERSIST} )
    private List<Device> devices = new ArrayList<>();

}
