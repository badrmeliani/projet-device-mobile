package com.valueit.device.domaine;

import com.valueit.device.service.model.Device;
import com.valueit.device.service.model.Emp;
import com.valueit.device.service.model.Role;
import com.valueit.device.service.model.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class EmpConverter {
    public static EmpVo toVo(Emp bo) {
        if (bo == null || bo.getId() == null)
            return null;
        EmpVo vo = new EmpVo();
        vo.setId(bo.getId());
       vo.setUsername(bo.getUsername());
       vo.setPassword(bo.getPassword());
       vo.setRoles(RoleConverter.toVoList(bo.getRoles()));
        vo.setAccountNonExpired(bo.isAccountNonExpired());
        vo.setAccountNonLocked(bo.isAccountNonLocked());
        vo.setCredentialsNonExpired(bo.isCredentialsNonExpired());
        vo.setEnabled(bo.isEnabled());
        vo.setSalary(bo.getSalary());
        vo.setFonction(bo.getFonction());
        vo.setAuthorities(getAuthorities(bo.getRoles()));
        vo.setEntreprise(EntrepriseConverter.toVo(bo.getEntreprise()));
        return vo;
    }

    private static Collection<? extends GrantedAuthority> getAuthorities(List<Role > roles) {
        List<GrantedAuthority> springSecurityAuthorities = new ArrayList<>();
        roles.forEach(r -> springSecurityAuthorities.add(new SimpleGrantedAuthority(r.getRole())));

        roles.forEach(r->r.getPrivileges().forEach(p->springSecurityAuthorities.add(new SimpleGrantedAuthority(p.getPrivilege()))));


        return springSecurityAuthorities;
    }

    public static Emp emp (EmpVo userVo) {
        Emp emp = new Emp();
        emp.setId(userVo.getId());
        emp.setUsername(userVo.getUsername());
        emp.setPassword(userVo.getPassword());
        emp.setRoles(RoleConverter.toBoList(userVo.getRoles()));
        emp.setAccountNonExpired(userVo.getAccountNonExpired());
        emp.setAccountNonLocked(userVo.isAccountNonLocked());
        emp.setCredentialsNonExpired(userVo.isCredentialsNonExpired());
        emp.setEnabled(userVo.getEnabled());
        emp.setSalary(userVo.getSalary());
        emp.setFonction(userVo.getFonction());
        emp.setEntreprise(EntrepriseConverter.toBo(userVo.getEntreprise()));


        return  emp;
    }
    public static Emp tobe (EmpVo userVo) {
        Emp emp = new Emp();
        emp.setId(userVo.getId());
        if (userVo.getUsername() != null) {
            emp.setUsername(userVo.getUsername());
        }
        if (userVo.getPassword() != null) {
            emp.setPassword(userVo.getPassword());
        }
        if (userVo.getRoles()!=null){
            emp.setRoles(RoleConverter.toBoList(userVo.getRoles()));
        }

        if (userVo.getAccountNonExpired() != null) {
            emp.setAccountNonExpired(userVo.getAccountNonExpired());
        }
        emp.setAccountNonLocked(userVo.isAccountNonLocked());
        emp.setCredentialsNonExpired(userVo.isCredentialsNonExpired());
        if ((userVo.getEnabled())!= null){
            emp.setEnabled(userVo.getEnabled());
        }
        if (userVo.getSalary() !=null) {
            emp.setSalary(userVo.getSalary());
        }
        if (userVo.getFonction() !=null){
            emp.setFonction(userVo.getFonction());
        }
        if (userVo.getEntreprise()!=null){
            emp.setEntreprise(EntrepriseConverter.toBo(userVo.getEntreprise()));
        }

        return  emp;

    }

    public static List<EmpVo> toListVo(List<Emp> listBo) {
        List<EmpVo> listVo = new ArrayList<>();
        for (Emp emp : listBo) {
            listVo.add(toVo(emp));
        }
        return listVo;
    }






}
