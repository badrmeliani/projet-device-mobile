package com.valueit.device.domaine;

import com.valueit.device.service.model.Entreprise;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class EntrepriseConverter {
    public static EntrepriseVo toVo(Entreprise entreprise) {
        EntrepriseVo entrepriseVo = new EntrepriseVo();
        entrepriseVo.setId(entreprise.getId());
        entrepriseVo.setNom(entreprise.getNom());
        entrepriseVo.setAdresse(entreprise.getAdresse());
        entrepriseVo.setCapitale(entreprise.getCapitale());
        entrepriseVo.setFondateur(entreprise.getFondateur());
        entrepriseVo.setSecteur(entreprise.getSecteur());
        entrepriseVo.setDate_creation(entreprise.getDate_creation());

        return entrepriseVo;
    }

    public static Entreprise toBo(EntrepriseVo entrepriseVo) {
        Entreprise entreprise1 = new Entreprise();
        entreprise1.setId(entrepriseVo.getId());
        if(entrepriseVo.getNom() != null) {
            entreprise1.setNom(entrepriseVo.getNom());
        }
        if (entrepriseVo.getAdresse() != null) {
            entreprise1.setAdresse(entrepriseVo.getAdresse());
        }
        entreprise1.setCapitale(entrepriseVo.getCapitale());

        if (entrepriseVo.getFondateur() !=null) {
            entreprise1.setFondateur(entrepriseVo.getFondateur());
        }
        if (entrepriseVo.getSecteur() !=null){
            entreprise1.setSecteur(entrepriseVo.getSecteur());
        }
        if (entrepriseVo.getDate_creation() != null) {
            entreprise1.setDate_creation(entrepriseVo.getDate_creation());
        } else {
            entreprise1.setDate_creation(new Date());
        }

        return entreprise1;
    }

    public static List<EntrepriseVo> toListVo(List<Entreprise> list)  {
        List<EntrepriseVo> voList = new ArrayList<>();
        for (Entreprise entreprise:list) {
            voList.add(toVo(entreprise));
        }
        return voList;
    }
}


