package com.valueit.device.domaine;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;

import java.util.ArrayList;
import java.util.List;


@Data
@NoArgsConstructor
public class RoleVo {
    private int id;
//    private String role;
    private String role;
    private List<PrivilegeVo> privileges = new ArrayList<>();

//    public RoleVo(String role) {
//        this.role = role;
//    }

//    public RoleVo( String role) {
//
//        this.role = role;
//    }



    public RoleVo(String role) {
        this.role = role;
    }

    public RoleVo(String role, List<PrivilegeVo> privileges) {
        this.role = role;
        this.privileges = privileges;
    }


}
