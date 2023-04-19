package com.valueit.device.domaine;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;

import java.io.Serializable;
@Data
@NoArgsConstructor

public class PrivilegeVo implements GrantedAuthority, Serializable {
    private Long id;
    private String privilege;
    @Override
    public String getAuthority() {
         return this.privilege;
    }
    public PrivilegeVo(String privilege) {
        this.privilege = privilege;
    }
}
