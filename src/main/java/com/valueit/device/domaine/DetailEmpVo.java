package com.valueit.device.domaine;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import java.util.List;


@Data
public class DetailEmpVo extends UserVo{
    private Long id;
    private String username;
    private Double salary;
    @NotEmpty(message = "La fonction de l'employé ne peut pas être vide")
    private String fonction;
    private EntrepriseVo entreprise;
    private List<DeviceVo> devices;


    public DetailEmpVo() {
        super();
    }

    public DetailEmpVo( Double salary, String fonction) {
        this.salary = salary;
        this.fonction = fonction;
    }


    public DetailEmpVo(String username, String password, List<RoleVo> roles, boolean accountNonExpired, boolean accountNonLocked, boolean credentialsNonExpired, boolean enabled,  Double salary, String fonction, EntrepriseVo entreprise,List<DeviceVo> devices) {
        super(username, password, roles, accountNonExpired, accountNonLocked, credentialsNonExpired, enabled);
        this.salary = salary;
        this.fonction = fonction;
        this.entreprise = entreprise;
        this.devices = devices;
    }
}
