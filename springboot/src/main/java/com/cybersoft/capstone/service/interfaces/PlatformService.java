package com.cybersoft.capstone.service.interfaces;

import java.util.List;

import com.cybersoft.capstone.dto.PlatformCreateDTO;
import com.cybersoft.capstone.entity.Platforms;

public interface PlatformService {
    public Platforms createPlatform(PlatformCreateDTO platformDTO);
    public List<Platforms> getAllPlatforms(Boolean isOrphan);
    public Platforms getPlatformById(int id);
    public Platforms updatePlatform(int id, Platforms platform);
    public Void deletePlatform(int id);
}
