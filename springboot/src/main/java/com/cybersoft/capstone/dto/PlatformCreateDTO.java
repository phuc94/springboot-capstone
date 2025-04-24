package com.cybersoft.capstone.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public record PlatformCreateDTO (
    Integer id,
    String name,
    String title,
    @JsonProperty("parent_id") Integer parentId
) {
}
