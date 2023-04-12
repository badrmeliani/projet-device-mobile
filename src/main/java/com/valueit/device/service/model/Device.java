package com.valueit.device.service.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
public class Device {
    @Id
    @GeneratedValue
    private Long numSrie;
    private String marque;
    private String modele;
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "entreprise")
    private Entreprise entreprise;

    public Device(String marque, String modele) {
        this.marque = marque;
        this.modele = modele;
    }
}
