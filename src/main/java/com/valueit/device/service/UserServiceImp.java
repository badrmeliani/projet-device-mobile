package com.valueit.device.service;

import com.valueit.device.dao.DeviceRepositoty;
import com.valueit.device.dao.RoleRepository;
import com.valueit.device.dao.UserRepository;
import com.valueit.device.domaine.RoleConverter;
import com.valueit.device.domaine.RoleVo;
import com.valueit.device.domaine.UserConverter;
import com.valueit.device.domaine.UserVo;
import com.valueit.device.service.exception.BusinessException;
import com.valueit.device.service.model.User;
import com.valueit.device.service.model.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
@Service("userService")
@Transactional
public class UserServiceImp implements IUserService {
    @Autowired
    private UserRepository userRepository1;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    @Autowired
    private RoleRepository roleRepository;




    @Override

    public void save(UserVo userVo) {
        User user = UserConverter.toBo(userVo);
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        List<Role> rolesPersist = new ArrayList<>();
        for (Role role : user.getRoles()) {
            Role userRole = roleRepository.findByRole(role.getRole()).get(0);
            rolesPersist.add(userRole);
        }
        user.setRoles(rolesPersist);
        userRepository1.save(user);


    }


    public void save(RoleVo role) {
        roleRepository.save(RoleConverter.toBo(role));

    }

    public RoleVo findByRole(String role) {
        return null;
    }

    @Override
    public List<UserVo> getAllUsers() {
        List<User> list = userRepository1.findAll();
        return UserConverter.toVoList(list);
    }

    @Override
    public List<RoleVo> getAllRoles() {
        List<Role> roles = roleRepository.findAll();
        return RoleConverter.toVoList(roles);
    }

    @Override
    public RoleVo getRoleByName(String role) {
        return RoleConverter.toVo(roleRepository.findByRole(role).get(0));

    }

//    public void addRoleToUser(String role, String userName) {
//        UserVo userVo = UserConverter.toVo(userRepository1.findByUserName(userName));
//        RoleVo roleVo = RoleConverter.toVo(roleRepository.findByRole(role));
//       userVo.getRoles().add(roleVo);
//
//    }

    @Override
    public void cleanDataBase() {
        userRepository1.deleteAll();
        roleRepository.deleteAll();

    }

    @Override
    public boolean existsByUsername(String username) {
        return userRepository1.existsByUsername(username);
    }

    @Override
    public boolean existsByRole(String role) {
        return false;
    }

    @Override
    public UserVo findByUsername(String username) {
        if (username == null || username.trim().equals(""))
            throw new BusinessException("login is empty !!");

        User bo = userRepository1.findByUserName(username);

        if (bo == null)
            throw new BusinessException("No user with this login");

        UserVo vo = UserConverter.toVo(bo);
        return vo;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return UserConverter.toVo(userRepository1.findByUserName(username));
    }
}
