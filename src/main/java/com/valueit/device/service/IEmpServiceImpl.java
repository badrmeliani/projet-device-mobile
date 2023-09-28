package com.valueit.device.service;

import com.valueit.device.dao.EmpRepository;
import com.valueit.device.dao.RoleRepository;
import com.valueit.device.dao.UserRepository;
import com.valueit.device.domaine.EmpConverter;
import com.valueit.device.domaine.EmpVo;
import com.valueit.device.service.model.Emp;
import com.valueit.device.service.model.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;
@Service
@Transactional
public class IEmpServiceImpl implements IEmpService {
    @Autowired
    private EmpRepository empRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private UserRepository userRepository;
    @Override
    public List<EmpVo> getEmployees() {
        List<Emp> list = empRepository.findAll();
        return EmpConverter.toListVo(list);
    }
    @Override
    public void save(EmpVo empVo) {
        Emp emp = EmpConverter.emp(empVo);

        // Fetch the role based on the fonction
        Role role = roleRepository.findByRole(emp.getFonction());

        if (role == null) {
            // Handle the case where the role doesn't exist for the fonction
            throw new RuntimeException("Role not found for fonction: " + empVo.getFonction());
        }

        // Assign the role to the user
        emp.setRoles(Collections.singletonList(role));
        userRepository.save(emp);
    }
    @Override
    public EmpVo getEmpById(Long id) {
        boolean trouve = empRepository.existsById(id);
        if (!trouve)
            return null;
        return EmpConverter.toVo(empRepository.getById(id));
    }
    @Override
    public void delete(Long id) {
        empRepository.deleteById(id);
    }
    @Override
    public List<EmpVo> findBySalary(Double salaty) {
        List<Emp> list = empRepository.findBySalary(salaty);
        return EmpConverter.toListVo(list);
    }
    @Override
    public List<EmpVo> findByFonction(String fonction) {
        List<Emp> list = empRepository.findByFonction(fonction);
        return EmpConverter.toListVo(list);
    }
    @Override
    public EmpVo getEmpHavingMaxSalary() {
        return EmpConverter.toVo(empRepository.getEmpHavaingMaxSalary());
    }
    @Override
    public List<EmpVo> findAll(int pageId, int size) {
        Page<Emp> result = empRepository.findAll(PageRequest.of(pageId, size, Sort.Direction.ASC, "name"));
        return EmpConverter.toListVo(result.getContent());
    }
    @Override
    public List<EmpVo> sortBy(String fieldName) {
        return EmpConverter.toListVo(empRepository.findAll(Sort.by(fieldName)));
    }
}
