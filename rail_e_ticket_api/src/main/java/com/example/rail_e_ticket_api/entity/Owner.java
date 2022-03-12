package com.example.rail_e_ticket_api.entity;

import com.example.rail_e_ticket_api.entity.base.BaseEntity;
import com.example.rail_e_ticket_api.util.enums.RolePermissionEnum;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.Column;
import javax.persistence.Entity;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Owner extends BaseEntity implements UserDetails {
    private String name;

    @Column(unique = true)
    private String username;

    @Column(unique = true)
    private String email;

    private String password;

    private int permissions;

    {
        super.setActive(false);
    }



    boolean accountNonExpired = true;
    boolean accountNonLocked = true;
    boolean credentialsNonExpired = true;
    boolean enabled = true;



    @JsonIgnore
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if(permissions==0)
            return null;
        Set<SimpleGrantedAuthority> authorities=new HashSet<>();
        for (RolePermissionEnum value : RolePermissionEnum.values())
            if((permissions & value.getCode())!=0)
                authorities.add(new SimpleGrantedAuthority(value.name()));
        return authorities;
    }

    public Owner(String name, String username, String email, String password, int permissions,boolean active) {
        this.name = name;
        this.username = username;
        this.email = email;
        this.password = password;
        this.permissions = permissions;
        super.setActive(active);
    }

    public Owner(String username, int permissions) {
        this.username = username;
        this.permissions = permissions;
    }
}
