package com.valueit.device.domaine;

import com.valueit.device.service.model.Entreprise;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotEmpty;
import java.util.List;

@Data

public class EmpVo extends UserVo{
//    private Long id;
    //Bean validation (est implémenté par Hibernate Validator
//    @NotEmpty(message = "Le nom de l'employé ne peut pas être vide")
//    private String name;
    @Max(value = 15000, message = "Le salaire ne doit pas dépasser 15000 ")
    private Double salary;
    @NotEmpty(message = "La fonction de l'employé ne peut pas être vide")
    private String fonction;
    private Entreprise entreprise;

    public EmpVo() {
        super();
    }

//    public EmpVo(Long id, String name, Double salary, String fonction) {
//        this(name, salary, fonction);
////        this.id = id;
//    }

    public EmpVo(String name, Double salary, String fonction) {
//        this.name = name;
        this.salary = salary;
        this.fonction = fonction;
    }

//    public EmpVo(Long id, Double salary, String fonction, Entreprise entreprise) {
//        this.id = id;
//        this.salary = salary;
//        this.fonction = fonction;
//        this.entreprise = entreprise;
//    }

    public EmpVo(String username, String password, List<RoleVo> roles, boolean accountNonExpired, boolean accountNonLocked, boolean credentialsNonExpired, boolean enabled,  Double salary, String fonction/* Entreprise entreprise*/) {
        super(username, password, roles, accountNonExpired, accountNonLocked, credentialsNonExpired, enabled);
//        this.name = name;
        this.salary = salary;
        this.fonction = fonction;
//        this.entreprise = entreprise;
    }
}
