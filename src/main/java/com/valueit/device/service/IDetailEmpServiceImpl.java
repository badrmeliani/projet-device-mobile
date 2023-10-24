package com.valueit.device.service;

import com.valueit.device.dao.EmpRepository;
import com.valueit.device.dao.RoleRepository;
import com.valueit.device.dao.UserRepository;
import com.valueit.device.domaine.*;
import com.valueit.device.service.exception.BusinessException;
import com.valueit.device.service.model.Emp;
import com.valueit.device.service.model.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;

@Service
@Transactional
public class IDetailEmpServiceImpl implements IDetailEmpService {
    @Autowired
    private EmpRepository empRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private UserRepository userRepository;

    @Override
    public List<DetailEmpVo> getDetailEmployees() {
        List<Emp> list = empRepository.findAll();
        return DetailEmpConverter.toListVo(list);
    }

    @Override
    public long getCount() {
        return empRepository.count();
    }

    @Override
    public void save(DetailEmpVo detailEmpVo) {

        Emp emp = DetailEmpConverter.emp(detailEmpVo);

        // Fetch the role based on the fonction
        Role role = roleRepository.findByRole(emp.getFonction());

        if (role == null) {
            // Handle the case where the role doesn't exist for the fonction
            throw new RuntimeException("Role not found for fonction: " + detailEmpVo.getFonction());
        }

        // Assign the role to the user
        emp.setRoles(Collections.singletonList(role));

        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String hashedPassword = encoder.encode(detailEmpVo.getPassword());
        emp.setPassword(hashedPassword);
        if(detailEmpVo.getEntreprise() != null){
            emp.setEntreprise(EntrepriseConverter.toBo(detailEmpVo.getEntreprise()));
        }
        userRepository.save(emp);
    }

    @Override
    public DetailEmpVo getDetailEmpById(Long id) {
        boolean trouve = empRepository.existsById(id);
        if (!trouve)
            return null;
        return DetailEmpConverter.toVo(empRepository.getById(id));
    }

    @Override
    public void delete(Long id) {
        empRepository.deleteById(id);
    }
    @Override
    public List<RoleVo> getAllRoles() {
        List<Role> roles = roleRepository.findAll(); // Assuming you have a repository for roles
        return RoleConverter.toVoList(roles); // Convert roles to RoleVo
    }
    @Override
    public List<DetailEmpVo> findBySalary(Double salary) {
        List<Emp> list = empRepository.findBySalary(salary);
        return DetailEmpConverter.toListVo(list);
    }
    @Override
    public List<DetailEmpVo> findByFonction(String fonction) {
        List<Emp> list = empRepository.findByFonction(fonction);
        return DetailEmpConverter.toListVo(list);
    }
    @Override
    public DetailEmpVo findByUsername(String username) {
        if (username == null || username.trim().equals(""))
            throw new BusinessException("login is empty !!");

        Emp bo = empRepository.findByUsername(username);

        if (bo == null)
            throw new BusinessException("No user with this login");

        return DetailEmpConverter.toVo(bo);

    }

//    @Override
//    public DetailEmpVo getDetailEmpHavingMaxSalary() {
//        return DetailEmpConverter.toVo(empRepository.getEmpHavaingMaxSalary());
//    }
    @Override
    public List<DetailEmpVo> findAll(int pageId, int size) {
        Page<Emp> result = empRepository.findAll(PageRequest.of(pageId, size, Sort.Direction.ASC, "name"));
        return DetailEmpConverter.toListVo(result.getContent());
    }
    @Override
    public List<DetailEmpVo> sortBy(String fieldName) {
        return DetailEmpConverter.toListVo(empRepository.findAll(Sort.by(fieldName)));
    }
}
