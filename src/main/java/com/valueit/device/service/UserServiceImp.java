package com.valueit.device.service;

import com.valueit.device.dao.EmpRepository;
import com.valueit.device.dao.PrivilegeRepository;
import com.valueit.device.dao.RoleRepository;
import com.valueit.device.dao.UserRepository;
import com.valueit.device.domaine.*;
import com.valueit.device.service.exception.BusinessException;
import com.valueit.device.service.model.Emp;
import com.valueit.device.service.model.Privilege;
import com.valueit.device.service.model.User;
import com.valueit.device.service.model.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
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
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private PrivilegeRepository privilegeRepository;
    @Autowired
    EmpRepository empRepository;

    @Override
    public void save(PrivilegeVo vo) {
        Privilege bo = PrivilegeConverter.toBo(vo);
        Privilege privilegePersist = privilegeRepository.findByPrivilege(bo.getPrivilege());
        if (privilegePersist == null)
            privilegePersist = privilegeRepository.save(bo);
    }

    @Override
    public void save(RoleVo vo) {
        Role bo = RoleConverter.toBo(vo);
        Role rolePersist = roleRepository.findByRole(bo.getRole());
        List<Privilege> privilegeList = new ArrayList<>();
        if (rolePersist == null) {
            vo.getPrivileges().forEach(p -> {
                Privilege privilege = PrivilegeConverter.toBo(p);
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
    public void save(EmpVo empVo) {
        Emp emp = EmpConverter.emp(empVo);
        emp.setPassword(passwordEncoder.encode(empVo.getPassword()));
        userRepository1.save(emp);
    }

    @Override

    public void save(UserVo vo) {
        User bo = UserConverter.toBo(vo);
        String plainPassword = vo.getPassword();
        String encodedPassword = passwordEncoder.encode(plainPassword);
        bo.setPassword(encodedPassword);

        List<Role> roleList = new ArrayList<>();
        bo.getRoles().forEach(r -> {
            Role rolePersist = roleRepository.findByRole(r.getRole());
            List<Privilege> privilegeList = new ArrayList<>();
            if (rolePersist == null) {
                r.getPrivileges().forEach(p -> {
                    Privilege privilegePersist = privilegeRepository.findByPrivilege(p.getPrivilege());
                    if (privilegePersist == null)
                        privilegePersist = privilegeRepository.save(p);
                    privilegeList.add(privilegePersist);

                });
                r.setPrivileges(privilegeList);
                rolePersist = roleRepository.save(r);
            }
            roleList.add((Role) rolePersist);
        });
        bo.setRoles(roleList);
        userRepository1.save(bo);

    }

     @Override

    public RoleVo findByRole(String role) {

      return RoleConverter.toVo(roleRepository.findByRole(role));
    }

    @Override
    public void delete(long id) {
        userRepository1.deleteById(id);
    }

    @Override
    public UserVo getuserById(Long id) {
        boolean trouver = userRepository1.existsById(id);
        if (!trouver) return null;
        return UserConverter.toVo(userRepository1.getById(id));

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

        return UserConverter.toVo(bo);

    }

    @Override
    public List<UserVo> findAll(int pageId, int size) {
        Page<User> result = userRepository1.findAll(PageRequest.of(pageId,size, Sort.Direction.ASC,"username"));
        return UserConverter.toVoList(result.getContent());
    }

    @Override
    public List<UserVo> sortBy(String fieldName) {
        return UserConverter.toVoList(userRepository1.findAll(Sort.by(Sort.Direction.ASC,fieldName)));
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return UserConverter.toVo(userRepository1.findByUsername(username));
    }

}
