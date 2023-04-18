package com.valueit.device.domaine;

import com.valueit.device.service.model.Role;
import com.valueit.device.service.model.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class UserConverter {
    public static UserVo toVo(User bo) {
        if (bo == null)
            return null;
        UserVo vo = new UserVo();
        vo.setId(bo.getId());
        vo.setUsername(bo.getUsername());
        vo.setPassword(bo.getPassword());

        vo.setRoles(RoleConverter.toVoList(bo.getRoles()));
        vo.setAccountNonExpired(bo.isAccountNonExpired());
        vo.setAccountNonLocked(bo.isAccountNonLocked());
        vo.setCredentialsNonExpired(bo.isCredentialsNonExpired());
        vo.setEnabled(bo.isEnabled());
        return vo;
    }
    private static Collection<? extends GrantedAuthority> getAuthorities(List<Role> roles) {
        List<GrantedAuthority> springSecurityAuthorities = new ArrayList<>();
        roles.forEach(r -> springSecurityAuthorities.add(new SimpleGrantedAuthority(r.getRole())));
        return springSecurityAuthorities;
    }

    public static User toBo(UserVo vo) {
        if (vo == null)
            return null;
        User bo = new User();
        if (vo.getId() != null)
            bo.setId(vo.getId());
        bo.setUsername(vo.getUsername());
        bo.setPassword(vo.getPassword());
        bo.setRoles(RoleConverter.toBoList(vo.getRoles()));
        bo.setRoles(RoleConverter.toBoList(vo.getRoles()));
        bo.setAccountNonExpired(vo.isAccountNonExpired());
        bo.setAccountNonLocked(vo.isAccountNonLocked());
        bo.setCredentialsNonExpired(vo.isCredentialsNonExpired());
        bo.setEnabled(vo.isEnabled());
        return bo;
    }

    public static List<UserVo> toVoList(List<User> boList) {
        List<UserVo> voList = new ArrayList<>();
        for (User user : boList) {
            voList.add(toVo(user));
        }
        return voList;
    }

    public static List<User> toBoList(List<UserVo> voList) {
        List<User> boList = new ArrayList<>();
        for (UserVo userVo : voList) {
            boList.add(toBo(userVo));
        }
        return boList;
    }
}
