package com.cybersoft.capstone.dto;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.cybersoft.capstone.entity.Admins;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import lombok.Data;

@Data
public class CustomAdminDetails implements UserDetails {
    private int id;
    private String name;
    private String email;
    private RoleDTO role;

    public CustomAdminDetails(Admins admin) {
      this.id = admin.getId();
      this.name = admin.getName();
      this.email = admin.getEmail();
      RoleDTO role = new RoleDTO(admin.getRole().getId(), admin.getRole().getTitle());
      this.role = role;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<RoleDTO> roles = new ArrayList<RoleDTO>();
        roles.add(role);
        return roles;
    }

    @Override
    public String getPassword() { return ""; }
    @Override
    public String getUsername() { return ""; }
    @Override
    public boolean isAccountNonExpired() { return true; }
    @Override
    public boolean isAccountNonLocked() { return true; }
    @Override
    public boolean isCredentialsNonExpired() { return true; }
    @Override
    public boolean isEnabled() { return true; }
}
