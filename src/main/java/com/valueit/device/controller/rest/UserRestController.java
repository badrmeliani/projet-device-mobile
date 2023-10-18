package com.valueit.device.controller.rest;


import com.valueit.device.domaine.RoleVo;

import com.valueit.device.domaine.UserVo;
import com.valueit.device.service.IEmpService;
import com.valueit.device.service.IUserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


import javax.validation.Valid;

import java.util.*;


@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/api/users")


public class UserRestController {
    @Autowired
    private IEmpService service;
    @Autowired
    IUserService iUserService;

    @GetMapping(value = "/read", produces = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE})

    List<UserVo> getAllUser() {
        return iUserService.getAllUsers();
    }


     @GetMapping(value = "/read/{username}")
     UserVo findByUsername( String username) {
        return iUserService.findByUsername(username);

    }
    @GetMapping("/read/{id}")
    public ResponseEntity<UserVo> getUserById(@PathVariable Long id) {
        UserVo userVo = iUserService.getuserById(id);
        if (userVo == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(userVo, HttpStatus.OK);
    }

    @GetMapping("/read/{roleName}")
    public ResponseEntity<RoleVo> findByRole(@PathVariable String roleName) {
        RoleVo role = iUserService.findByRole(roleName);
        if (role != null) {
            return new ResponseEntity<>(role, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @GetMapping("/roles")
    public ResponseEntity<List<RoleVo>> getAllRoles() {
        List<RoleVo> roles = iUserService.getAllRoles();
        return new ResponseEntity<>(roles, HttpStatus.OK);
    }

    @PostMapping(value = "/create")
    ResponseEntity<Object> createUser(@RequestBody @Valid UserVo userVo) {
        iUserService.save(userVo);
        return new ResponseEntity<>("User is create", HttpStatus.CREATED);
    }

    @PutMapping(value = "/update/{id}")
    ResponseEntity<Object> updateuser(@PathVariable(name = "id") long id,@Valid @RequestBody UserVo userVo) {
        UserVo empFound = iUserService.getuserById(id);
        if (empFound==null) return ResponseEntity.notFound().build();
        userVo.setId(id);
        iUserService.save(userVo);
        return new ResponseEntity<>("update user succesful",HttpStatus.OK);
    }

    @DeleteMapping(value = "/delete/{id}")
    ResponseEntity<Object> deleteuser(@PathVariable (name = "id") Long empVoId) {
        UserVo empFound = iUserService.getuserById(empVoId);
        if (empFound==null) return ResponseEntity.notFound().build();
      iUserService.delete(empVoId);
        return new ResponseEntity<>("User is deleted successfully", HttpStatus.OK);

    }

    @GetMapping(value = "/sort/{fieldName}", produces = { MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE })
    public List<UserVo> sortBy(@PathVariable String fieldName) {
        return iUserService.sortBy(fieldName);
    }
    @GetMapping("/pagination/{pageid}/{size}")
    public List<UserVo> pagination(@PathVariable int pageid, @PathVariable int size, Model m) {
        return iUserService.findAll(pageid, size);
    }
}




