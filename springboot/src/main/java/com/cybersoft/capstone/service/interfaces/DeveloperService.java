package com.cybersoft.capstone.service.interfaces;

import com.cybersoft.capstone.entity.Developers;
import com.cybersoft.capstone.payload.response.BaseResponse;

import java.util.List;

public interface DeveloperService {
    public BaseResponse<List<Developers>> getAllDevelopers();
    public BaseResponse<Developers> getDeveloperById(int id);
    public BaseResponse<Developers> createDeveloper(Developers developer);
    public BaseResponse<Developers> updateDeveloper(int id, Developers developer);
    public BaseResponse<Void> deleteDeveloperById(int id);
}
