package com.valueit.device.domaine;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;


import java.util.Date;

@Data
@NoArgsConstructor

public class EntrepriseVo {
    private Long id;
    private String nom;
    private String adresse;
    private int capitale;
    private String fondateur;
    private String secteur;
    private Date date_creation;
    private List<DeviceVo> devices;



    public EntrepriseVo(Long id, String nom, String adresse, int capitale, String fondateur, String secteur, Date date_creation,List<DeviceVo> devices) {
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
