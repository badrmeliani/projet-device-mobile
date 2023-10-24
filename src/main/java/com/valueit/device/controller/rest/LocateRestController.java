package com.valueit.device.controller.rest;

import com.valueit.device.domaine.DeviceVo;
import com.valueit.device.domaine.LocationVo;
import com.valueit.device.service.IDeviceservices;
import com.valueit.device.service.ILocationServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
public class LocateRestController {

    @Autowired
    IDeviceservices iDeviceservices;

    @Autowired
    ILocationServices iLocationServices;

    @PostMapping(value = "/mobile/devices/location")
    public ResponseEntity<Object> sendLocation(@Valid @RequestBody LocationVo locationVo) {
        System.out.println("getLatitude="+locationVo.getLatitude()+" getLongitude="+locationVo.getLongitude());
        if (iLocationServices.sendLocation(locationVo)){
            return new ResponseEntity<>("succès", HttpStatus.OK);
        }
        return new ResponseEntity<>("echeque", HttpStatus.NOT_FOUND);
    }
}
