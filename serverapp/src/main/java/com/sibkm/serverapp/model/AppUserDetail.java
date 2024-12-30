package com.sibkm.serverapp.model;

import com.sibkm.serverapp.entity.User;
import com.sibkm.serverapp.entity.Role;
import lombok.AllArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.*;

@AllArgsConstructor
public class AppUserDetail implements UserDetails {

    private User user;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> authorities = new ArrayList<>();

        Set<Role> roles = user.getRole();
        if (roles != null) {
            for (Role role : roles) {

                authorities.add(new SimpleGrantedAuthority("ROLE_" + role.getRole().toUpperCase()));

                role.getPriviledges().forEach(priviledge -> {
                    String priviledgeName = priviledge.getPriviledge().toUpperCase();
                    authorities.add(new SimpleGrantedAuthority(priviledgeName));
                });
            }
        }

        return authorities;
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getUsername();
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
