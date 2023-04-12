package com.valueit.device.domaine;

import com.valueit.device.service.model.Entreprise;

import java.util.ArrayList;
import java.util.List;

public class EntrepriseConverter {
    public static EntrepriseVo toVo(Entreprise entreprise) {
        EntrepriseVo entrepriseVo = new EntrepriseVo();
        entrepriseVo.setId(entreprise.getId());
        entrepriseVo.setNom(entreprise.getNom());
        entrepriseVo.setAdresse(entreprise.getAdresse());
        entrepriseVo.setCapitale(entreprise.getCapitale());
        entrepriseVo.setFondateur(entreprise.getFondateur());
        return entrepriseVo;
    }

    public static Entreprise toBo(EntrepriseVo entrepriseVo) {
        Entreprise entreprise1 = new Entreprise();
        entreprise1.setId(entrepriseVo.getId());
        entreprise1.setNom(entrepriseVo.getNom());
        entreprise1.setAdresse(entrepriseVo.getAdresse());
        entreprise1.setCapitale(entrepriseVo.getCapitale());
        entreprise1.setFondateur(entrepriseVo.getFondateur());
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


