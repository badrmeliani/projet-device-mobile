package com.valueit.device.domaine;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class DeviceVo {
    private Long numSrie;
    private String marque;
    String modele;

    public DeviceVo(String marque, String modele) {
        this.marque = marque;
        this.modele = modele;
    }
}
