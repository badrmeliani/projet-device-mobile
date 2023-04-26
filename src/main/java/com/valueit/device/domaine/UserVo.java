package com.valueit.device.domaine;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.validation.constraints.NotEmpty;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
@Data
@NoArgsConstructor


public class UserVo implements UserDetails {
    private Long id;


    private String username;
//    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @NotEmpty
    private String password;

    private Boolean accountNonLocked;
    private boolean credentialsNonExpired;
    private Boolean enabled;
    private Boolean accountNonExpired;

    private  Collection<? extends GrantedAuthority> authorities = new ArrayList<>();
    private List<RoleVo> roles = new ArrayList<RoleVo>();
    public UserVo(String username, String password, List<RoleVo> roles, boolean accountNonExpired,
                  boolean accountNonLocked, boolean credentialsNonExpired, boolean enabled) {
        this.username = username;
        this.password = password;
        this.roles = roles;
        this.accountNonExpired=accountNonExpired;
        this.accountNonLocked=accountNonLocked;
        this.credentialsNonExpired=credentialsNonExpired;
        this.enabled=enabled;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.authorities;
    }



    @Override
    public boolean isAccountNonExpired() {
        return this.accountNonExpired;
    }

    @Override
    public boolean isAccountNonLocked() {
        return this.accountNonLocked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return this.credentialsNonExpired;
    }

    @Override
    public boolean isEnabled() {
        return this.enabled;
    }
}
