package com.valueit.device.service;

import com.valueit.device.dao.DeviceRepositoty;
import com.valueit.device.domaine.DeviceConverter;
import com.valueit.device.domaine.DeviceVo;
import com.valueit.device.domaine.LocationVo;
import com.valueit.device.service.model.Device;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class LocationServicesImp implements ILocationServices {
    @Autowired
    DeviceRepositoty deviceRepositoty;

    @Override
    public Boolean sendLocation(LocationVo locationVo) {

        List<Device> devices = deviceRepositoty.findByNumSrie(locationVo.getSerialDevice());
        if (devices.isEmpty()) {
            return false;
        }
        devices.forEach( device -> {
            device.setLatitude(locationVo.getLatitude());
            device.setLongitude(locationVo.getLongitude());
        });
        deviceRepositoty.saveAll(devices);
        return true;
    }
}
