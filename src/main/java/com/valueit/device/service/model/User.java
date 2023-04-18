package com.valueit.device.service.model;

import lombok.Data;

import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import java.util.ArrayList;
import java.util.List;


@Entity
@Data
@NoArgsConstructor
@Table(name = "user")

public class User {
    @Id
    @GeneratedValue
    private Long id;

    @Length(min = 5, message = "*Your username must have at least 5 characters")
    @NotEmpty(message = "*Please provide an user name")
    private String username;

    @Length(min = 5, message = "*Your an email")
    @NotEmpty(message = "*Please provide an email")
    @Email
    private String email;

    @Length(min = 5, message = "*Your password must have at least 5 characters")
    @NotEmpty(message = "*Please provide your password")
    private String password;

    @ManyToMany(cascade = CascadeType.ALL )
    @JoinTable(name = "user_roles", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
    private List<Role> roles = new ArrayList<>();
      private boolean enabled ;
    private boolean accountNonExpired;
    private boolean credentialsNonExpired ;
    private boolean accountNonLocked;







}
