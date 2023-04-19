package com.valueit.device.domaine;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;


@Data
@NoArgsConstructor
public class RoleVo {
    private int id;
    private String role;
    private String role2;
    private List<PrivilegeVo> privileges = new ArrayList<>();

//    public RoleVo(String role) {
//        this.role = role;
//    }

    public RoleVo(String role, String role2) {
        this.role = role;
        this.role2 = role2;
    }
}
