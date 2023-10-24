package com.valueit.device.service;

import com.valueit.device.domaine.DeviceVo;
import com.valueit.device.domaine.LocationVo;
import com.valueit.device.service.model.Device;

import java.util.List;

public interface ILocationServices {
    Boolean sendLocation(LocationVo locationVo);

}
