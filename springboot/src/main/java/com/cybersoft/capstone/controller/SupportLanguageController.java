package com.cybersoft.capstone.controller;

import com.cybersoft.capstone.entity.SupportLanguages;
import com.cybersoft.capstone.payload.response.BaseResponse;
import com.cybersoft.capstone.service.implement.SupportLanguageServiceImpl;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/support_language")
public class SupportLanguageController {
    private final SupportLanguageServiceImpl supportLanguageService;

    @Autowired
    public SupportLanguageController(SupportLanguageServiceImpl supportLanguageService) {
        this.supportLanguageService = supportLanguageService;
    }

    @GetMapping
    public BaseResponse<List<SupportLanguages>> getAllSupportLanguages() {
        return supportLanguageService.getAllSupportLanguages();
    }

    @GetMapping("/{id}")
    public BaseResponse<SupportLanguages> getSupportLanguageById(@PathVariable int id) {
        return supportLanguageService.getSupportLanguageById(id);
    }

    @PostMapping
    public BaseResponse<SupportLanguages> createSupportLanguage(@Valid @RequestBody SupportLanguages supportLanguage) {
        return supportLanguageService.createSupportLanguage(supportLanguage);
    }

    @PostMapping("/{id}")
    public BaseResponse<SupportLanguages> updateSupportLanguage(@Valid @PathVariable int id, @Valid @RequestBody SupportLanguages supportLanguage) {
        return supportLanguageService.updateSupportLanguage(id, supportLanguage);
    }

    @DeleteMapping("/{id}")
    public BaseResponse<Void> deleteSupportLanguage(@Valid @PathVariable int id) {
        return supportLanguageService.deleteSupportLanguage(id);
    }
}
