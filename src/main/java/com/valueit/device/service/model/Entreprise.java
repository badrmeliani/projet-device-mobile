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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_entreprise")
    private Long id;
    private String nom;
    private String adresse;
    private int capitale;
    private String fondateur;
    private String secteur;
    @Column(name = "date_creation", columnDefinition = "DATE")
    private Date date_creation;

    public Entreprise(Long id, String nom, String adresse, int capitale, String fondateur, String secteur, Date date_creation, List<Device> devices) {
        this.id = id;
        this.nom = nom;
        this.adresse = adresse;
        this.capitale = capitale;
        this.fondateur = fondateur;
        this.secteur = secteur;
        this.date_creation = date_creation;
        this.devices = devices;
    }


    @OneToMany(fetch = FetchType.LAZY, mappedBy = "entreprise",cascade = {CascadeType.MERGE,CascadeType.PERSIST} )
    private List<Device> devices = new ArrayList<>();


}
