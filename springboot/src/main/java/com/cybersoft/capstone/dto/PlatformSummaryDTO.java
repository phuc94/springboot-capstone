package com.cybersoft.capstone.dto;

import java.util.List;

import lombok.Data;

@Data
public class PlatformSummaryDTO {
    private String name;
    private String title;
    private List<GameCardDTO> games;
}
