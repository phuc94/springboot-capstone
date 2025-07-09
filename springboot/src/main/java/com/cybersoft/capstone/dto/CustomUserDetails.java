package com.cybersoft.capstone.dto;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.cybersoft.capstone.entity.Carts;
import com.cybersoft.capstone.entity.Users;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import lombok.Data;

@Data
public class CustomUserDetails implements UserDetails {
    private int id;
    private String name;
    private String email;
    private RoleDTO role;
    private Carts cart;

    public CustomUserDetails(Users user) {
      this.id = user.getId();
      this.name = user.getName();
      this.email = user.getEmail();
      this.cart = user.getCart();
      RoleDTO role = new RoleDTO(user.getRole().getId(), user.getRole().getTitle());
      this.role = role;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<RoleDTO> roles = new ArrayList<RoleDTO>();
        roles.add(role);
        return roles;
    }

    public SimpleUserDTO getSimpleUserDTO() {
        SimpleUserDTO simpleUserDTO = new SimpleUserDTO();
        simpleUserDTO.setId(id);
        simpleUserDTO.setName(name);
        simpleUserDTO.setEmail(email);
        return simpleUserDTO;
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

