package com.valueit.device.domaine;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor

public class EntrepriseVo {
    private Long id;
    private String nom;
    private String adresse;
    private int capitale;
    private String fondateur;
    private Date date_creation;

    public EntrepriseVo(String nom, String adresse, int capitale, String fondateur) {
        this.nom = nom;
        this.adresse = adresse;
        this.capitale = capitale;
        this.fondateur = fondateur;
    }
}
