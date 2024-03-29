package com.valueit.device.controller.rest;

import com.valueit.device.domaine.DeviceVo;
import com.valueit.device.service.IDeviceservices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
public class DeviceRestController {
    @Autowired

    IDeviceservices iDeviceservices;

    @GetMapping(value = "/api/devices/count")
    public long getCount(){
        return iDeviceservices.getCount();
    }

    @GetMapping(value = "/api/devices/read", produces = { MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE })

    public List<DeviceVo> getAll() {
        return iDeviceservices.getAll();
    }
    @GetMapping(value = "/api/devices/read/{id}")
    public ResponseEntity<Object> getDeviceById(@PathVariable(value = "id") Long deviceId) {
        DeviceVo deviceFound = iDeviceservices.getById(deviceId);
        if (deviceFound == null)
            return new ResponseEntity<>("device doesn't exist", HttpStatus.OK);
        return new ResponseEntity<>(deviceFound, HttpStatus.OK);
    }

    @PostMapping(value = "/api/devices/create")
    public ResponseEntity<Object> createDevice(@Valid @RequestBody DeviceVo deviceVo) {
        iDeviceservices.save(deviceVo);
        return new ResponseEntity<>("device is created successfully", HttpStatus.CREATED);
    }
    @PutMapping(value = "/api/devices/update/{id}")
    public ResponseEntity<Object> updatedevice(@PathVariable(name = "id") Long deviceId, @RequestBody DeviceVo deviceVo) {
        DeviceVo deviceFound = iDeviceservices.getById(deviceId);
        if ( deviceFound== null)
            return new ResponseEntity<>("device doen't exist", HttpStatus.OK);
        deviceVo.setId(deviceId);
        iDeviceservices.save(deviceVo);
        return new ResponseEntity<>("device is updated successfully", HttpStatus.OK);
    }
    @GetMapping(value = "/api/devices/sort/{modele}", produces = { MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE })

    public List<DeviceVo> sortBy(@PathVariable String modele) {
        return iDeviceservices.sortBy(modele);
    }
    @GetMapping("/api/devices/pagination/{pageid}/{size}")

    public List<DeviceVo> pagination(@PathVariable int pageid, @PathVariable int size, Model m) {
        return iDeviceservices.findAll(pageid, size);
    }
    @GetMapping("/api/devices/read/marque/{marque}")

    public List<DeviceVo> findByMarque(@PathVariable(name = "marque") String marque) {
        return iDeviceservices.findByMarque(marque);
    }
    @GetMapping("/api/devices/read/numSrie/{numSrie}")

    public List<DeviceVo> findbyNumSrie(@PathVariable(name = "numSrie") String numSrie) {
        return iDeviceservices.getByNumSrie(numSrie);
    }

    @GetMapping(value = "/api/devices/read/modele/{modele}", produces = { MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE })
    public List<DeviceVo> findByModele(@PathVariable(name = "modele") String modele) {
        return iDeviceservices.findByModele(modele);
    }


    @DeleteMapping(value = "/api/devices/delete/{id}")
    public ResponseEntity<Object> deleteEmp(@PathVariable(name = "id") Long deviceId) {
        DeviceVo deviceFound = iDeviceservices.getById(deviceId);
        if (deviceFound == null)
            return new ResponseEntity<>("device doen't exist", HttpStatus.OK);
        iDeviceservices.deleteById(deviceId);
        return new ResponseEntity<>("device is deleted successsfully", HttpStatus.OK);
    }

    @DeleteMapping(value = "/api/devices/delete")
    public ResponseEntity<Object> deleteAll() {
        iDeviceservices.deleteAll();
        return new ResponseEntity<>("All devices are deleted successsfully", HttpStatus.OK);
    }
}
