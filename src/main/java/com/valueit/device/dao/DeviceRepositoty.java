package com.valueit.device.dao;

import com.valueit.device.service.model.Device;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Transactional
public interface DeviceRepositoty extends JpaRepository<Device,Long> {

    List<Device> findByNumSrie(String numSrie);

    List<Device> findByMarque(String marque);
    List<Device> findByModele(String model);

}
