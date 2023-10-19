package com.valueit.device.domaine;

import com.valueit.device.service.model.Emp;
import com.valueit.device.service.model.Role;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class DetailEmpConverter {
    public static DetailEmpVo toVo(Emp bo) {
        if (bo == null || bo.getId() == null)
            return null;
        DetailEmpVo vo = new DetailEmpVo();
        vo.setId(bo.getId());
       vo.setUsername(bo.getUsername());
       vo.setPassword(bo.getPassword());
       vo.setRoles(RoleConverter.toVoList(bo.getRoles()));
        vo.setAccountNonExpired(bo.isAccountNonExpired());
        vo.setAccountNonLocked(bo.isAccountNonLocked());
        vo.setCredentialsNonExpired(bo.isCredentialsNonExpired());
        vo.setEnabled(bo.isEnabled());
        vo.setAuthorities(getAuthorities(bo.getRoles()));
        vo.setSalary(bo.getSalary());
        vo.setFonction(bo.getFonction());

        vo.setEntreprise(EntrepriseConverter.toVo(bo.getEntreprise()));
        return vo;
    }

    private static Collection<? extends GrantedAuthority> getAuthorities(List<Role > roles) {
        List<GrantedAuthority> springSecurityAuthorities = new ArrayList<>();
        roles.forEach(r -> springSecurityAuthorities.add(new SimpleGrantedAuthority(r.getRole())));

        roles.forEach(r->r.getPrivileges().forEach(p->springSecurityAuthorities.add(new SimpleGrantedAuthority(p.getPrivilege()))));


        return springSecurityAuthorities;
    }

    public static Emp emp (DetailEmpVo empVo) {
        Emp emp = new Emp();
        emp.setId(empVo.getId());
        emp.setUsername(empVo.getUsername());
        emp.setPassword(empVo.getPassword());
        emp.setRoles(RoleConverter.toBoList(empVo.getRoles()));
        emp.setAccountNonExpired(empVo.getAccountNonExpired());
        emp.setAccountNonLocked(empVo.isAccountNonLocked());
        emp.setCredentialsNonExpired(empVo.isCredentialsNonExpired());
        emp.setEnabled(empVo.getEnabled());
        emp.setSalary(empVo.getSalary());
        emp.setFonction(empVo.getFonction());
        emp.setEntreprise(EntrepriseConverter.toBo(empVo.getEntreprise()));


        return  emp;
    }
    public static Emp tobe (DetailEmpVo empVo) {
        Emp emp = new Emp();
        emp.setId(empVo.getId());
        if (empVo.getUsername() != null) {
            emp.setUsername(empVo.getUsername());
        }
        if (empVo.getPassword() != null) {
            emp.setPassword(empVo.getPassword());
        }
        if (empVo.getRoles()!=null){
            emp.setRoles(RoleConverter.toBoList(empVo.getRoles()));
        }

        if (empVo.getAccountNonExpired() != null) {
            emp.setAccountNonExpired(empVo.getAccountNonExpired());
        }
        emp.setAccountNonLocked(empVo.isAccountNonLocked());
        emp.setCredentialsNonExpired(empVo.isCredentialsNonExpired());
        if ((empVo.getEnabled())!= null){
            emp.setEnabled(empVo.getEnabled());
        }
        if (empVo.getSalary() !=null) {
            emp.setSalary(empVo.getSalary());
        }
        if (empVo.getFonction() !=null){
            emp.setFonction(empVo.getFonction());
        }
        if (empVo.getEntreprise()!=null){
            emp.setEntreprise(EntrepriseConverter.toBo(empVo.getEntreprise()));
        }

        return  emp;

    }

    public static List<DetailEmpVo> toListVo(List<Emp> listBo) {
        List<DetailEmpVo> listVo = new ArrayList<>();
        for (Emp emp : listBo) {
            listVo.add(toVo(emp));
        }
        return listVo;
    }

    public static Emp toEmp(DetailEmpVo empVo, Emp emp) {
        emp.setSalary(empVo.getSalary());
        emp.setFonction(empVo.getFonction());
        emp.setEntreprise(EntrepriseConverter.toBo(empVo.getEntreprise()));
        return  emp;
    }





}
