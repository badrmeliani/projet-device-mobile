package com.valueit.device.service.model;




import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.lang.Nullable;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor



public class Role {

    @Id
    @GeneratedValue
    @Column(name = "role_id")
    private int id;

private String role;

private String role2;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "role_privilege", joinColumns = @JoinColumn(name = "role_id"), inverseJoinColumns = @JoinColumn(name = "privilege_id"))
    private List<Privilege> privileges = new ArrayList<>();
    @ManyToMany(mappedBy = "roles")
    private List<User> users = new ArrayList<>();

//    public Role(String role) {
//        super();
//
//        this.role = role;
//    }

    public Role(String role, String role2) {
        this.role = role;
        this.role2 = role2;
    }
}
