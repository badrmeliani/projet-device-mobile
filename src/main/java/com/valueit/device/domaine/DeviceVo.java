package com.valueit.device.domaine;

import com.valueit.device.service.model.Entreprise;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor

public  class DeviceVo {
    private Long numSrie;
    private String marque;
    private String modele;
    private Entreprise entreprise;

    public DeviceVo(Long numSrie, String marque, String modele,Entreprise entreprise) {
        this.numSrie = numSrie;
        this.marque = marque;
        this.modele = modele;
        this.entreprise = entreprise;
    }
}
