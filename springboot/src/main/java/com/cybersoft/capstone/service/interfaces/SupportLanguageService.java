package com.cybersoft.capstone.service.interfaces;

import com.cybersoft.capstone.entity.SupportLanguages;
import com.cybersoft.capstone.payload.response.BaseResponse;

import java.util.List;
import java.util.Optional;

public interface SupportLanguageService {
    public BaseResponse<List<SupportLanguages>> getAllSupportLanguages();

    public BaseResponse<SupportLanguages> getSupportLanguageById(int id);

    public BaseResponse<SupportLanguages> createSupportLanguage(SupportLanguages supportLanguage);

    public BaseResponse<SupportLanguages> updateSupportLanguage(int id, SupportLanguages supportLanguage);

    public BaseResponse<Void> deleteSupportLanguage(int id);
}
