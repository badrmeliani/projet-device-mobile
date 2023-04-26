package com.valueit.device.controller.rest;


import com.valueit.device.domaine.RoleVo;

import com.valueit.device.domaine.UserVo;
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

public class UserRestController {
    @Autowired
    IUserService iUserService;

    @GetMapping(value = "/users/view", produces = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE})

    List<UserVo> getAllUser() {
        return iUserService.getAllUsers();
    }


//     @GetMapping(value = "/admin/user/{username}")
//    UserVo findByUsername( String username) {
//        return iUserService.findByUsername(username);
//
//    }




    @PostMapping(value = "/users/create")
//    @PreAuthorize("hasRole('ADMIN')")
    ResponseEntity<Object> createUser(@RequestBody @Valid UserVo userVo) {
        iUserService.save(userVo);
        return new ResponseEntity<>("User is create", HttpStatus.CREATED);
    }

    @PutMapping(value = "/users/update/{name}")
    ResponseEntity<Object> updateuser(@PathVariable(name = "name") long id,@Valid @RequestBody UserVo userVo) {
        UserVo empFound = iUserService.getuserById(id);
        if (empFound==null) return ResponseEntity.notFound().build();
        userVo.setId(id);
        iUserService.save(userVo);
        return new ResponseEntity<>("update user succesful",HttpStatus.OK);
    }

    @DeleteMapping(value = "/users/delete/{id}")
    ResponseEntity<Object> deleteuser(@PathVariable (name = "id") Long empVoId) {
        UserVo empFound = iUserService.getuserById(empVoId);
        if (empFound==null) return ResponseEntity.notFound().build();
      iUserService.delete(empVoId);
        return new ResponseEntity<>("User is deleted successsfully", HttpStatus.OK);

    }

    @GetMapping(value = "/users/sort/{fieldName}", produces = { MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE })
    public List<UserVo> sortBy(@PathVariable String fieldName) {
        return iUserService.sortBy(fieldName);
    }
        @GetMapping("/users/pagination/{pageid}/{size}")
    public List<UserVo> pagination(@PathVariable int pageid, @PathVariable int size, Model m) {
        return iUserService.findAll(pageid, size);
    }




//    @PostMapping(value = "/addRoleToUserName")
//    public void save(@Valid @RequestBody RoleUserForm roleUserForm) {
//        iUserService.addRoleToUser(roleUserForm.getUserName(), roleUserForm.getRole());
//    }

}




