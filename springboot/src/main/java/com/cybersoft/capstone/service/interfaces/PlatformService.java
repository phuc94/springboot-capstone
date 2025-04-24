package com.cybersoft.capstone.service.interfaces;

import com.cybersoft.capstone.dto.PlatformCreateDTO;
import com.cybersoft.capstone.entity.Platforms;
import com.cybersoft.capstone.payload.response.BaseResponse;

import java.util.List;

public interface PlatformService {
    public BaseResponse<Platforms> createPlatform(PlatformCreateDTO platformDTO);
    public BaseResponse<List<Platforms>> getAllPlatforms(Boolean isOrphan);
    public BaseResponse<Platforms> getPlatformById(int id);
    public BaseResponse<Platforms> updatePlatform(int id, Platforms platform);
    public BaseResponse<Void> deletePlatform(int id);
}
