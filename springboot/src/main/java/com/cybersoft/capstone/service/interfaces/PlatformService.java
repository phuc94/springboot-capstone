package com.cybersoft.capstone.service.interfaces;

import com.cybersoft.capstone.entity.Platforms;
import com.cybersoft.capstone.payload.response.BaseResponse;

import java.util.List;

public interface PlatformService {
    public BaseResponse<Platforms> createPlatform(Platforms platform);
    public BaseResponse<List<Platforms>> getAllPlatforms();
    public BaseResponse<Platforms> getPlatformById(int id);
    public BaseResponse<Platforms> updatePlatform(int id, Platforms platform);
    public BaseResponse<Void> deletePlatform(int id);
}
