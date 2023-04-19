package com.valueit.device.service.model;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@Builder
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

    public Device(long l, String marque, String modele, Entreprise valueIt) {
        this.marque = marque;
        this.modele = modele;
    }
}
