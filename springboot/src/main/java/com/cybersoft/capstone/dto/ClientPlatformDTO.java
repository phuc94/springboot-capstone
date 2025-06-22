package com.cybersoft.capstone.dto;

import java.util.List;

import lombok.Data;

@Data
public class ClientPlatformDTO {
    private int id;
    private String name;
    private String title;
    private List<ClientPlatformDTO> children;
}

