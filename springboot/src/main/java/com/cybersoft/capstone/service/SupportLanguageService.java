package com.cybersoft.capstone.service;

import com.cybersoft.capstone.entity.SupportLanguages;
import com.cybersoft.capstone.repository.SupportLanguageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SupportLanguageService {
    private final SupportLanguageRepository supportLanguageRepository;

    public SupportLanguageService(SupportLanguageRepository supportLanguageRepository) {
        this.supportLanguageRepository = supportLanguageRepository;
    }

    public List<SupportLanguages> getAllSupportLanguages() {
        return supportLanguageRepository.findAll();
    }

    public Optional<SupportLanguages> getSupportLanguageById(int id) {
        return supportLanguageRepository.findById(id);
    }

    public SupportLanguages createSupportLanguage(SupportLanguages supportLanguage) {
        return supportLanguageRepository.save(supportLanguage);
    }

    public Optional<SupportLanguages> updateSupportLanguage(int id, SupportLanguages supportLanguage) {
        return supportLanguageRepository.findById(id).map(existingSupportLanguage -> {
            existingSupportLanguage.setLanguage(supportLanguage.getLanguage());
            return supportLanguageRepository.save(existingSupportLanguage);
        });
    }

    public boolean deleteSupportLanguage(int id) {
        if (!supportLanguageRepository.existsById(id)) {
            return false;
        }
        supportLanguageRepository.deleteById(id);
        return true;
    }
}
