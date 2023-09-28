package com.valueit.device.controller.rest;

import com.valueit.device.domaine.DeviceVo;
import com.valueit.device.domaine.EntrepriseVo;
import com.valueit.device.service.IEntrepriseService;
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
public class EntrepriseRestController {
    @Autowired
    IEntrepriseService entrepriseServiceImp;
    @GetMapping(value = "/api/entreprise/read", produces = { MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE })

    List<EntrepriseVo> getAll(){
        return entrepriseServiceImp.getAll();
    }

    @GetMapping("/api/entreprise/read/{id}")
    public ResponseEntity<EntrepriseVo> getById(@PathVariable(name = "id") Long id) {
        EntrepriseVo entrepriseVo = entrepriseServiceImp.getById(id);
        if (entrepriseVo == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(entrepriseVo);
    }
    @GetMapping("/api/entreprise/read/nom/{nom}")

    public List<EntrepriseVo> findByNameEntreprise(@PathVariable(name = "nom") String nom) {
        return entrepriseServiceImp.findByNameEntreprise(nom);
    }
    @PostMapping(value = "/api/entreprise/create")

    ResponseEntity<Object> createUser(@RequestBody @Valid EntrepriseVo entrepriseVo) {
        entrepriseServiceImp.save(entrepriseVo);
        return new ResponseEntity<>("entreprise is create", HttpStatus.CREATED);
    }
    @DeleteMapping(value = "/api/entreprise/delete/{id}")

    ResponseEntity<Object> deleteById(@PathVariable(name = "id") Long id) {
        EntrepriseVo found = entrepriseServiceImp.getById(id);
        if (found==null) return ResponseEntity.notFound().build();
        entrepriseServiceImp.deleteById(id);
        return new ResponseEntity<>("delete",HttpStatus.ACCEPTED);
    }
    @PutMapping(value = "/api/entreprise/update/{id}")

    public ResponseEntity<Object> updatedevice(@PathVariable(name = "id") Long entrepriseId, @RequestBody EntrepriseVo entrepriseVo) {
        EntrepriseVo entrepriseVo1 = entrepriseServiceImp.getById(entrepriseId);
        if ( entrepriseVo1== null)
            return new ResponseEntity<>("Entreprise doen't exist", HttpStatus.OK);
        entrepriseVo.setId(entrepriseId);
       entrepriseServiceImp.save(entrepriseVo);
        return new ResponseEntity<>("entreprise is updated successfully", HttpStatus.OK);
    }

    @GetMapping(value = "/api/entreprise/sort/{nom}", produces = { MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE })

    public List<EntrepriseVo> sortBy(@PathVariable String nom) {
        return entrepriseServiceImp.sortBy(nom);
    }

    @GetMapping("/api/entreprise/pagination/{pageid}/{size}")

    public List<EntrepriseVo> pagination(@PathVariable int pageid, @PathVariable int size, Model m) {
        return entrepriseServiceImp.findAll(pageid, size);
    }

}
