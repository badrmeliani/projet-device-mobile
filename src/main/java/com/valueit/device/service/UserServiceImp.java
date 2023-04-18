package com.valueit.device.service;

import com.valueit.device.dao.PrivilegeRepository;
import com.valueit.device.dao.RoleRepository;
import com.valueit.device.dao.UserRepository;
import com.valueit.device.domaine.*;
import com.valueit.device.service.exception.BusinessException;
import com.valueit.device.service.model.Privilege;
import com.valueit.device.service.model.User;
import com.valueit.device.service.model.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
@Service("userService")
@Transactional
public class UserServiceImp implements IUserService {
    @Autowired
    private UserRepository userRepository1;
    @Autowired
   private PasswordEncoder passwordEncoder;

    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private PrivilegeRepository privilegeRepository;




    @Override

    public void save(UserVo vo) {
        User bo = UserConverter.toBo(vo);
        bo.setPassword(passwordEncoder.encode(vo.getPassword()));
        List<Role> roleList = new ArrayList<>();
        bo.getRoles().forEach(r -> {
//            Role rolePersist = roleRepository.findByRole(r.getRole());
           List<Role> rolePersist = roleRepository.findByRole(r.getRole());
            List<Privilege> privilegeList = new ArrayList<>();
            if (rolePersist == null) {
                r.getPrivileges().forEach(p -> {
                    Privilege privilegePersist = privilegeRepository.findByPrivilege(p.getPrivilege());
                    if (privilegePersist == null)
                        privilegePersist = privilegeRepository.save(p);
                    privilegeList.add(privilegePersist);

                });
                r.setPrivileges(privilegeList);
                rolePersist = (List<Role>) roleRepository.save(r);
            }
            roleList.add((Role) rolePersist);
        });
        bo.setRoles(roleList);
        userRepository1.save(bo);


    }


    public void save(RoleVo vo) {
        Role bo = RoleConverter.toBo(vo);
        List<Role> rolePersist = roleRepository.findByRole(bo.getRole());
        List<Privilege> privilegeList = new ArrayList<>();
        if (rolePersist == null) {
            vo.getPrivileges().forEach(p -> {
                Privilege privilege= PrivilegeConverter.toBo(p);
                Privilege privilegePersist = privilegeRepository.findByPrivilege(privilege.getPrivilege());
                if (privilegePersist == null)
                    privilegePersist = privilegeRepository.save(privilege);
                privilegeList.add(privilegePersist);

            });
            bo.setPrivileges(privilegeList);
            roleRepository.save(bo);
        }
    }

    @Override
    public void save(PrivilegeVo vo) {
        Privilege bo = PrivilegeConverter.toBo(vo);
        Privilege privilegePersist = privilegeRepository.findByPrivilege(bo.getPrivilege());
        if (privilegePersist == null)
            privilegePersist = privilegeRepository.save(bo);
    }


//    public RoleVo findByRole(String role) {
//      List<Role> findRole = roleRepository.findByRole(role);
//      return RoleConverter.toVoList(findRole);
//    }

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

        return roleRepository.existsByRole(role);
    }

    @Override
    public UserVo findByUsername(String username) {
        if (username == null || username.trim().equals(""))
            throw new BusinessException("login is empty !!");

        User bo = userRepository1.findByUsername(username);

        if (bo == null)
            throw new BusinessException("No user with this login");

        UserVo vo = UserConverter.toVo(bo);
        return vo;
    }



    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return UserConverter.toVo(userRepository1.findByUsername(username));
    }
}
