package com.valueit.device.domaine;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor

public  class LocationVo {
    private String serialDevice;
    private Double latitude;
    private Double longitude;

    public LocationVo(String serialDevice, Double latitude, Double longitude) {
        this.serialDevice = serialDevice;
        this.latitude = latitude;
        this.longitude = longitude;
    }
}
