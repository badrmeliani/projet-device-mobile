package com.valueit.device.service.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Entity
@NoArgsConstructor
@Data

public class Entreprise {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nom;
    private String adresse;
    private int capitale;
    private String fondateur;
    private String secteur;
    @Column(name = "date_creation", columnDefinition = "DATE")
    private Date date_creation;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "entreprise", cascade = {CascadeType.ALL})
    @JsonManagedReference
//  @PrimaryKeyJoinColumn
    private List<Device> devices;

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


}
