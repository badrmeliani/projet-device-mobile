package com.valueit.device.service;

import com.valueit.device.domaine.EmpVo;
import com.valueit.device.domaine.RoleVo;
import com.valueit.device.domaine.UserVo;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

public interface IEmpService {
    List<EmpVo> getEmployees();

    long getCount();

    void save(EmpVo emp);
    EmpVo getEmpById(Long id);
    void delete(Long id);
    List<RoleVo> getAllRoles();
    List<EmpVo> findBySalary(Double salary);
    List<EmpVo> findByFonction(String designation);
    EmpVo findByUsername(String username);

    EmpVo getEmpHavingMaxSalary();
    //Pour la pagination
    List<EmpVo> findAll(int pageId, int size);
    //pour le tri
    List<EmpVo> sortBy(String fieldName);
}
