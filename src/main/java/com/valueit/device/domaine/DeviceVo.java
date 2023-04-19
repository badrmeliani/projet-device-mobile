package com.valueit.device.domaine;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor

public  class DeviceVo {
    private Long numSrie;
    private String marque;
    String modele;

    public DeviceVo(long l, String marque, String modele) {
        this.marque = marque;
        this.modele = modele;
    }
}
