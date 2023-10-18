package com.valueit.device.domaine;

import com.valueit.device.service.model.Entreprise;
import lombok.*;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotEmpty;
import java.util.List;

@Data
public class EmpVo extends UserVo{

    private Double salary;
    @NotEmpty(message = "La fonction de l'employé ne peut pas être vide")
    private String fonction;
    private EntrepriseVo entreprise;
    private List<DeviceVo> devices;


    public EmpVo() {
        super();
    }

//    public EmpVo(Long id, String name, Double salary, String fonction) {
//        this(name, salary, fonction);
////        this.id = id;
//    }

    public EmpVo( Double salary, String fonction) {
        this.salary = salary;
        this.fonction = fonction;
    }


    public EmpVo(String username, String password, List<RoleVo> roles, boolean accountNonExpired, boolean accountNonLocked, boolean credentialsNonExpired, boolean enabled,  Double salary, String fonction, EntrepriseVo entreprise,List<DeviceVo> devices) {
        super(username, password, roles, accountNonExpired, accountNonLocked, credentialsNonExpired, enabled);
        this.salary = salary;
        this.fonction = fonction;
        this.entreprise = entreprise;
        this.devices = devices;
    }

}
