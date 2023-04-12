package com.valueit.device.controller.rest;


import com.valueit.device.domaine.RoleVo;

import com.valueit.device.domaine.UserVo;
import com.valueit.device.service.IUserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import javax.validation.Valid;

import java.util.*;


@RestController
@CrossOrigin(origins = "*", maxAge = 3600)

public class UserRestController {
    @Autowired
    IUserService iUserService;

    @GetMapping(value = "/admin/user/view", produces = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE})

    List<UserVo> getAllUser() {
        return iUserService.getAllUsers();
    }

    @GetMapping(value = "/admin/role", produces = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE})

    List<RoleVo> getAllRoles() {
        return iUserService.getAllRoles();
    }



    @PostMapping(value = "/admin/user/create")
    ResponseEntity<Object> createUser(@RequestBody @Valid RoleVo roleVo) {
        iUserService.save(roleVo);
        return new ResponseEntity<>("Role is create", HttpStatus.CREATED);
    }
//
//    @PostMapping(value = "/addRoleToUserName")
//    public void save(@Valid @RequestBody RoleUserForm roleUserForm) {
//        iUserService.addRoleToUser(roleUserForm.getUserName(), roleUserForm.getRole());
//    }

}




