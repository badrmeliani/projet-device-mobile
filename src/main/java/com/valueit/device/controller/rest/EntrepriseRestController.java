package com.valueit.device.controller.rest;

import com.valueit.device.domaine.EntrepriseVo;
import com.valueit.device.service.IEntrepriseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
public class EntrepriseRestController {
    @Autowired
    IEntrepriseService entrepriseServiceImp;
    @GetMapping(value = "superadmin/entreprise", produces = { MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE })

    List<EntrepriseVo> getAll(){
        return entrepriseServiceImp.getAll();
    }
    @PostMapping(value = "/superadmin/entreprise")

    ResponseEntity<Object> createUser(@RequestBody @Valid EntrepriseVo entrepriseVo) {
        entrepriseServiceImp.save(entrepriseVo);
        return new ResponseEntity<>("entreprise is create", HttpStatus.CREATED);
    }
    @DeleteMapping(value = "/superadmin/entreprise/{id}")

    ResponseEntity<Object> deleteById(@PathVariable(name = "id") Long id) {
        EntrepriseVo found = entrepriseServiceImp.getById(id);
        if (found==null) return ResponseEntity.notFound().build();
        entrepriseServiceImp.deleteById(id);
        return new ResponseEntity<>("delete",HttpStatus.ACCEPTED);
    }
    @PutMapping(value = "/superadmin/entreprise/{id}")

    public ResponseEntity<Object> updatedevice(@PathVariable(name = "id") Long entrepriseId, @RequestBody EntrepriseVo entrepriseVo) {
        EntrepriseVo entrepriseVo1 = entrepriseServiceImp.getById(entrepriseId);
        if ( entrepriseVo1== null)
            return new ResponseEntity<>("Entreprise doen't exist", HttpStatus.OK);
        entrepriseVo.setId(entrepriseId);
       entrepriseServiceImp.save(entrepriseVo);
        return new ResponseEntity<>("entreprise is updated successsfully", HttpStatus.OK);
    }

}
