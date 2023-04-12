package com.valueit.device.service;

import com.valueit.device.domaine.RoleVo;
import com.valueit.device.domaine.UserVo;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface IUserService extends UserDetailsService  {
    void save(UserVo user);
    void save(RoleVo role);
    List<UserVo> getAllUsers();
    List<RoleVo> getAllRoles();
    RoleVo getRoleByName(String role);
    void cleanDataBase();
    boolean existsByUsername(String username);
    boolean existsByRole(String role);
    UserVo findByUsername(String username);
}
