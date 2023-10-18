package com.valueit.device.domaine;

import com.valueit.device.service.model.Emp;
import com.valueit.device.service.model.Entreprise;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor

public  class DeviceVo {
    private Long id;
    private String numSrie;
    private String marque;
    private String modele;
    private EntrepriseVo entreprise;
    private EmpVo emp;

    public DeviceVo(Long id, String numSrie, String marque, String modele,EntrepriseVo entreprise, EmpVo emp) {
        this.id = id;
        this.numSrie = numSrie;
        this.marque = marque;
        this.modele = modele;
        this.entreprise = entreprise;
        this.emp = emp;
    }
}
