package com.cybersoft.capstone.service.implement;

import com.cybersoft.capstone.entity.SupportLanguages;
import com.cybersoft.capstone.repository.SupportLanguageRepository;
import com.cybersoft.capstone.service.interfaces.SupportLanguageService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SupportLanguageServiceImpl implements SupportLanguageService {
    private final SupportLanguageRepository supportLanguageRepository;

    public SupportLanguageServiceImpl(SupportLanguageRepository supportLanguageRepository) {
        this.supportLanguageRepository = supportLanguageRepository;
    }

    @Override
    public List<SupportLanguages> getAllSupportLanguages() {
        return supportLanguageRepository.findAll();
    }

    @Override
    public Optional<SupportLanguages> getSupportLanguageById(int id) {
        return supportLanguageRepository.findById(id);
    }

    @Override
    public SupportLanguages createSupportLanguage(SupportLanguages supportLanguage) {
        return supportLanguageRepository.save(supportLanguage);
    }

    @Override
    public Optional<SupportLanguages> updateSupportLanguage(int id, SupportLanguages supportLanguage) {
        return supportLanguageRepository.findById(id).map(existingSupportLanguage -> {
            existingSupportLanguage.setLanguage(supportLanguage.getLanguage());
            return supportLanguageRepository.save(existingSupportLanguage);
        });
    }

    @Override
    public boolean deleteSupportLanguage(int id) {
        if (!supportLanguageRepository.existsById(id)) {
            return false;
        }
        supportLanguageRepository.deleteById(id);
        return true;
    }
}
