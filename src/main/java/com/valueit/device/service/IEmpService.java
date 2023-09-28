package com.valueit.device.service;

import com.valueit.device.domaine.EmpVo;

import java.util.List;

public interface IEmpService {
    List<EmpVo> getEmployees();
    void save(EmpVo emp);
    EmpVo getEmpById(Long id);
    void delete(Long id);
    List<EmpVo> findBySalary(Double salary);
    List<EmpVo> findByFonction(String designation);
    EmpVo getEmpHavingMaxSalary();
    //Pour la pagination
    List<EmpVo> findAll(int pageId, int size);
    //pour le tri
    List<EmpVo> sortBy(String fieldName);
}
