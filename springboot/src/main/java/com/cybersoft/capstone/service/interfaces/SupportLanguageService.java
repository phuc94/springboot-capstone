package com.cybersoft.capstone.service.interfaces;

import com.cybersoft.capstone.entity.SupportLanguages;

import java.util.List;
import java.util.Optional;

public interface SupportLanguageService {
    public List<SupportLanguages> getAllSupportLanguages();

    public Optional<SupportLanguages> getSupportLanguageById(int id);

    public SupportLanguages createSupportLanguage(SupportLanguages supportLanguage);

    public Optional<SupportLanguages> updateSupportLanguage(int id, SupportLanguages supportLanguage);

    public boolean deleteSupportLanguage(int id);
}
