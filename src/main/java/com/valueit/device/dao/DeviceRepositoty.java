package com.valueit.device.dao;

import com.valueit.device.service.model.Device;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DeviceRepositoty extends JpaRepository<Device,Long> {
    List<Device> findByMarque(String marque);
    List<Device> findByModele(String model);

}
