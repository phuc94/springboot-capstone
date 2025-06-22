package com.cybersoft.capstone.dto;

import java.util.Collection;
import java.util.List;

import com.cybersoft.capstone.entity.Carts;
import com.cybersoft.capstone.entity.Users;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class CustomUserDetails implements UserDetails {
    private Users user;

    public CustomUserDetails(Users user) {
      this.user = user;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of();
    }

    public String getEmail() {
        return user.getEmail();
    }

    public String getName() {
        return user.getName();
    }

    public int getId() {
        return user.getId();
    }

    public Carts getCart() {
        return user.getCart();
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

