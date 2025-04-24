package com.cybersoft.capstone.dto.mapper;

import com.cybersoft.capstone.dto.PlatformCreateDTO;
import com.cybersoft.capstone.entity.Platforms;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface PlatformMapper {
    @Mapping(ignore = true, target = "parent")
    Platforms toPlatform(PlatformCreateDTO platformCreateDTO);

    @Mapping(ignore = true, target = "parentId")
    PlatformCreateDTO toPlatformDTO(Platforms platform);
}
