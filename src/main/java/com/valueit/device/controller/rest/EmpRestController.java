package com.valueit.device.controller.rest;

import com.valueit.device.domaine.EmpVo;
import com.valueit.device.service.IEmpService;
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
public class EmpRestController {

    @Autowired
    private IEmpService service;

    @GetMapping(value = "/users/read", produces = { MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE })
    public List<EmpVo> getAll() {
        return service.getEmployees();
    }

    @GetMapping(value = "/users/read/{id}")
    public ResponseEntity<Object> getEmpById(@PathVariable(value = "id") Long empVoId) {
        EmpVo empVoFound = service.getEmpById(empVoId);
        if (empVoFound == null)
            return new ResponseEntity<>("Employee doesn't exist", HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(empVoFound, HttpStatus.OK);
    }

    @GetMapping(value = "/users/read/{fonction}", produces = { MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE })
    public List<EmpVo> findByFonction(@PathVariable String fonction) {
        return service.findByFonction(fonction);
    }

    @GetMapping(value = "/users/read/{salary}}", produces = { MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE })
    public List<EmpVo> findBySalary(@PathVariable Double salary) {
        return service.findBySalary(salary);
    }

    @PostMapping(value = "/users/create/")
    public ResponseEntity<Object> createEmp(@Valid @RequestBody EmpVo empVo) {
        service.save(empVo);
        return new ResponseEntity<>("Employee is created successfully", HttpStatus.CREATED);
    }

    @PutMapping(value = "/users/update/{id}")
    public ResponseEntity<Object> updateEmp(@PathVariable(name = "id") Long empVoId, @Valid @RequestBody EmpVo empVo) {
        EmpVo empVoFound = service.getEmpById(empVoId);
        if (empVoFound == null)
            return new ResponseEntity<>("Employee doesn't exist", HttpStatus.NOT_FOUND);
        empVo.setId(empVoId);
        service.save(empVo);
        return new ResponseEntity<>("Employee is updated successfully", HttpStatus.OK);
    }

    @DeleteMapping(value = "/users/delete/{id}")
    public ResponseEntity<Object> deleteEmp(@PathVariable(name = "id") Long empVoId) {
        EmpVo empVoFound = service.getEmpById(empVoId);
        if (empVoFound == null)
            return new ResponseEntity<>("Employee doesn't exist", HttpStatus.NOT_FOUND);
        service.delete(empVoId);
        return new ResponseEntity<>("Employee is deleted successfully", HttpStatus.OK);
    }

    @GetMapping(value = "/users/sort/{fieldName}", produces = { MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE })
    public List<EmpVo> sortBy(@PathVariable String fieldName) {
        return service.sortBy(fieldName);
    }

    @GetMapping("/users/pagination/{pageid}/{size}")
    public List<EmpVo> pagination(@PathVariable int pageid, @PathVariable int size, Model m) {
        return service.findAll(pageid, size);
    }
}
