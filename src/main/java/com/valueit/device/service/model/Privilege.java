package com.valueit.device.service.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
@NoArgsConstructor
@Data
@Entity

public class Privilege {

    @Id
    @GeneratedValue
    private Long id;
//    @Column(unique = true)
    private String privilege;



    @ManyToMany(mappedBy = "privileges")
    private List<Role> roles = new ArrayList<>();

    public Privilege(String privilege) {
        this.privilege = privilege;
    }


}
