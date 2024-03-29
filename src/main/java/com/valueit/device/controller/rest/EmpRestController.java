package com.valueit.device.controller.rest;

import com.valueit.device.domaine.EmpVo;
import com.valueit.device.domaine.RoleVo;
import com.valueit.device.domaine.UserVo;
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

    @GetMapping(value = "/api/employees/count")
    public long getCount(){
        return service.getCount();
    }

    @GetMapping(value = "/api/employees/read", produces = { MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE })
    public List<EmpVo> getAll() {
        return service.getEmployees();
    }

    @GetMapping(value = "/api/employees/read/{id}")
    public ResponseEntity<Object> getEmpById(@PathVariable(value = "id") Long empVoId) {
        EmpVo empVoFound = service.getEmpById(empVoId);
        if (empVoFound == null)
            return new ResponseEntity<>("Employee doesn't exist", HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(empVoFound, HttpStatus.OK);
    }

    @GetMapping(value = "/api/employees/read/{username}")
    EmpVo findByUsername(String username) {
        return service.findByUsername(username);

    }
    @GetMapping(value = "/api/employees/{roles}", produces = { MediaType.APPLICATION_JSON_VALUE })
     public List<RoleVo> getAllRoles() {
        return service.getAllRoles();
     }

    @GetMapping(value = "/api/employees/read/{fonction}", produces = { MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE })
    public List<EmpVo> findByFonction(@PathVariable String fonction) {
        return service.findByFonction(fonction);
    }

    @GetMapping(value = "/api/employees/read/{salary}", produces = { MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE })
    public List<EmpVo> findBySalary(@PathVariable Double salary) {
        return service.findBySalary(salary);
    }

    @PostMapping(value = "/api/employees/create")
    public ResponseEntity<Object> createEmp(@Valid @RequestBody EmpVo empVo) {
        service.save(empVo);
        return new ResponseEntity<>("Employee is created successfully", HttpStatus.CREATED);
    }

    @PutMapping(value = "/api/employees/update/{id}")
    public ResponseEntity<Object> updateEmp(@PathVariable(name = "id") Long empVoId, @Valid @RequestBody EmpVo empVo) {
        EmpVo empVoFound = service.getEmpById(empVoId);
        if (empVoFound == null)
            return new ResponseEntity<>("Employee doesn't exist", HttpStatus.NOT_FOUND);
        empVo.setId(empVoId);
        service.save(empVo);
        return new ResponseEntity<>("Employee is updated successfully", HttpStatus.OK);
    }

    @DeleteMapping(value = "/api/employees/delete/{id}")
    public ResponseEntity<Object> deleteEmp(@PathVariable(name = "id") Long empVoId) {
        EmpVo empVoFound = service.getEmpById(empVoId);
        if (empVoFound == null)
            return new ResponseEntity<>("Employee doesn't exist", HttpStatus.NOT_FOUND);
        service.delete(empVoId);
        return new ResponseEntity<>("Employee is deleted successfully", HttpStatus.OK);
    }

    @GetMapping(value = "/api/employees/sort/{fieldName}", produces = { MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE })
    public List<EmpVo> sortBy(@PathVariable String fieldName) {
        return service.sortBy(fieldName);
    }

    @GetMapping(value = "/api/employees/pagination/{pageid}/{size}")
    public List<EmpVo> pagination(@PathVariable int pageid, @PathVariable int size, Model m) {
        return service.findAll(pageid, size);
    }
}
