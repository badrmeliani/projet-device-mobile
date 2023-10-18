package com.valueit.device.domaine;

import com.valueit.device.service.model.Device;

import java.util.ArrayList;
import java.util.List;

public class DeviceConverter {
    public static DeviceVo toVo(Device devices) {
        DeviceVo deviceVo = new DeviceVo();
        deviceVo.setId(devices.getId());
        deviceVo.setNumSrie(devices.getNumSrie());
        deviceVo.setMarque(devices.getMarque());
        deviceVo.setModele(devices.getModele());
        deviceVo.setEntreprise(EntrepriseConverter.toVo(devices.getEntreprise()));
        deviceVo.setEmp(EmpConverter.toVo(devices.getEmp()));

        return deviceVo;
    }
    public static Device toBe(DeviceVo deviceVo) {
        Device devices = new Device();
        devices.setId(deviceVo.getId());
        devices.setNumSrie(deviceVo.getNumSrie());
        devices.setMarque(deviceVo.getMarque());
        devices.setModele(deviceVo.getModele());
        if(deviceVo.getEntreprise() != null) {
            devices.setEntreprise(EntrepriseConverter.toBo(deviceVo.getEntreprise()));
        }
        if(deviceVo.getEmp() != null) {
            devices.setEmp(EmpConverter.tobe(deviceVo.getEmp()));
        }
        return devices;
    }
    public static List<DeviceVo> ToList (List<Device> list) {
        List<DeviceVo> deviceVoList = new ArrayList<>();
        for (Device devices:list) {
            deviceVoList.add(toVo(devices));
        }
        return deviceVoList;
    }
}
