package com.valueit.device.service;

import com.valueit.device.dao.DeviceRepositoty;
import com.valueit.device.domaine.DeviceConverter;
import com.valueit.device.domaine.DeviceVo;
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


public class DeviceServicesImp implements IDeviceservices {
    @Autowired
    DeviceRepositoty deviceRepositoty;

    @Override
    public List<DeviceVo> getAll() {
        List<Device> devices = deviceRepositoty.findAll();
        return DeviceConverter.ToList(devices);
    }

    @Override
    public Device save(DeviceVo deviceVo) {
       Device device = deviceRepositoty.save(DeviceConverter.toBe(deviceVo));
       return device;
    }

    @Override
    public DeviceVo getById(Long id) {
        Optional<Device> device = deviceRepositoty.findById(id);
        return device.map(DeviceConverter::toVo)
                .orElseThrow(() -> new IllegalArgumentException("Device not found with ID: " + id));
    }

    @Override
    public List<DeviceVo> findByMarque(String marque) {
        List<Device> devices = deviceRepositoty.findByMarque(marque);
        return DeviceConverter.ToList(devices);
    }

    @Override
    public List<DeviceVo> findByModele(String modele) {
        List<Device> devices = deviceRepositoty.findByModele(modele);
        return DeviceConverter.ToList(devices);
    }

    @Override
    public void deleteById(Long id) {
        deviceRepositoty.deleteById(id);

    }

    @Override
    public void deleteAll() {
        deviceRepositoty.deleteAll();

    }

    @Override
    public List<DeviceVo> findAll(int pageId, int size) {
        Page<Device> devices = deviceRepositoty.findAll(PageRequest.of(pageId,size));
        return DeviceConverter.ToList(devices.getContent());
    }

    @Override
    public List<DeviceVo> sortBy(String modele) {
        List<Device> devices = deviceRepositoty.findAll(Sort.by(Sort.Direction.DESC,modele));
        return DeviceConverter.ToList(devices);
    }

}
