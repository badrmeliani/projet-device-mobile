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
        return vo;
    }

    public static Role toBo(RoleVo vo) {
        if (vo == null)
            return null;
        Role bo = new Role();
        bo.setId(vo.getId());
        bo.setRole(vo.getRole());
        return bo;
    }
    private static Collection<? extends GrantedAuthority> getAuthorities(List<Role> roles) {
        List<GrantedAuthority> springSecurityAuthorities = new ArrayList<>();
        for (Role r : roles) {
            springSecurityAuthorities.add(new SimpleGrantedAuthority(r.getRole()));
        }
        return springSecurityAuthorities;
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
