package com.cybersoft.capstone.dto;

import org.springframework.security.core.GrantedAuthority;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class RoleDTO implements GrantedAuthority {

    private int id;

    private String title;

    @Override
    public String getAuthority() {
      return this.title;
    }

}
