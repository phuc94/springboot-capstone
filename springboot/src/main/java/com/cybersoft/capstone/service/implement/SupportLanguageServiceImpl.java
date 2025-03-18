package com.cybersoft.capstone.service.implement;

import com.cybersoft.capstone.entity.SupportLanguages;
import com.cybersoft.capstone.exception.NotFoundException;
import com.cybersoft.capstone.payload.response.AcceptedResponse;
import com.cybersoft.capstone.payload.response.BaseResponse;
import com.cybersoft.capstone.payload.response.NotFoundResponse;
import com.cybersoft.capstone.payload.response.OkResponse;
import com.cybersoft.capstone.repository.SupportLanguageRepository;
import com.cybersoft.capstone.service.interfaces.SupportLanguageService;
import org.springframework.http.HttpStatus;
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
    public BaseResponse<List<SupportLanguages>> getAllSupportLanguages() {
        return new OkResponse<>(supportLanguageRepository.findAll());
    }

    @Override
    public BaseResponse<SupportLanguages> getSupportLanguageById(int id) {
        return supportLanguageRepository.findById(id)
                .map(OkResponse::new)
                .orElseThrow(() -> new NotFoundException(HttpStatus.NOT_FOUND.getReasonPhrase()));
    }

    @Override
    public BaseResponse<SupportLanguages> createSupportLanguage(SupportLanguages supportLanguage) {
        return new OkResponse<>(supportLanguageRepository.save(supportLanguage));
    }

    @Override
    public BaseResponse<SupportLanguages> updateSupportLanguage(int id, SupportLanguages supportLanguage) {
        return supportLanguageRepository.findById(id)
                .map(OkResponse::new)
                .orElseThrow(() -> new NotFoundException(HttpStatus.NOT_FOUND.getReasonPhrase()));
    }

    @Override
    public BaseResponse<Void> deleteSupportLanguage(int id) {
        if (supportLanguageRepository.existsById(id)) {
            supportLanguageRepository.deleteById(id);
            return new AcceptedResponse<>();
        }
        throw new NotFoundException(HttpStatus.NOT_FOUND.getReasonPhrase());
    }
}
