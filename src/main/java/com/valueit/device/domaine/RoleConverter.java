package com.valueit.device.domaine;

import com.valueit.device.service.model.Role;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class RoleConverter {
    public static RoleVo toVo(Role bo) {
        if (bo == null)
            return null;
        RoleVo vo = new RoleVo();
        vo.setId(bo.getId());
        vo.setRole(bo.getRole());
//        vo.setRole2(bo.getRole2());
        vo.setPrivileges(PrivilegeConverter.toVoList(bo.getPrivileges()));
        return vo;
    }

    public static Role toBo(RoleVo vo) {
        if (vo == null)
            return null;
        Role bo = new Role();
        bo.setId(vo.getId());
        if (vo.getRole() !=null){
            bo.setRole(vo.getRole());
        }
        if (vo.getPrivileges()!=null){
            bo.setPrivileges(PrivilegeConverter.toBoList(vo.getPrivileges()));
        }
        return bo;
    }

    public static List<RoleVo> toVoList(List<Role> boList) {
        List<RoleVo> voList = new ArrayList<>();
        for (Role role : boList) {
            voList.add(toVo(role));
        }
        return voList;
    }

    public static List<Role> toBoList(List<RoleVo> voList) {
        List<Role> boList = new ArrayList<>();
        for (RoleVo roleVo : voList) {
            boList.add(toBo(roleVo));
        }
        return boList;
    }
}
