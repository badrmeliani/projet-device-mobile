package com.valueit.device.service;

import com.valueit.device.domaine.DetailEmpVo;
import com.valueit.device.domaine.EmpVo;
import com.valueit.device.domaine.RoleVo;

import java.util.List;

public interface IDetailEmpService {
    List<DetailEmpVo> getDetailEmployees();

    long getCount();

    void save(DetailEmpVo detailEmpVo);
    DetailEmpVo getDetailEmpById(Long id);
    void delete(Long id);
    List<RoleVo> getAllRoles();
    List<DetailEmpVo> findBySalary(Double salary);
    List<DetailEmpVo> findByFonction(String designation);
    DetailEmpVo findByUsername(String username);

//    DetailEmpVo getDetailEmpHavingMaxSalary();
    //Pour la pagination
    List<DetailEmpVo> findAll(int pageId, int size);
    //pour le tri
    List<DetailEmpVo> sortBy(String fieldName);
}
