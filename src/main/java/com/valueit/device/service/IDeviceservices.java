package com.valueit.device.service;

import com.valueit.device.domaine.DeviceVo;
import com.valueit.device.service.model.Device;

import java.util.List;

public interface IDeviceservices {
    List<DeviceVo> getAll();

    long getCount();

    Device save(DeviceVo deviceVo);
    List<DeviceVo> getByNumSrie(String numSrie);

    DeviceVo getById(Long id);

    List<DeviceVo> findByMarque(String marque);
    List<DeviceVo> findByModele(String modele);
    void deleteById(Long id);
    void deleteAll();
    List<DeviceVo> findAll(int pageId, int size);
    //pour le tri
    List<DeviceVo> sortBy(String modele);
}
