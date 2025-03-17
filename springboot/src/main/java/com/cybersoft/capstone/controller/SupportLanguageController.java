package com.cybersoft.capstone.controller;

import com.cybersoft.capstone.entity.SupportLanguages;
import com.cybersoft.capstone.payload.response.BaseReponse;
import com.cybersoft.capstone.service.implement.SupportLanguageServiceImpl;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/support_language")
public class SupportLanguageController {
    private final SupportLanguageServiceImpl supportLanguageService;

    @Autowired
    public SupportLanguageController(SupportLanguageServiceImpl supportLanguageService) {
        this.supportLanguageService = supportLanguageService;
    }

    @GetMapping
    public BaseReponse getAllSupportLanguages() {
        return new BaseReponse(
                HttpStatus.OK.value(),
                HttpStatus.OK.getReasonPhrase(),
                supportLanguageService.getAllSupportLanguages()
        );
    }

    @GetMapping("/{id}")
    public BaseReponse getSupportLanguageById(@PathVariable int id) {
        return supportLanguageService.getSupportLanguageById(id)
                .map(supportLanguage -> new BaseReponse(
                        HttpStatus.OK.value(),
                        HttpStatus.OK.getReasonPhrase(),
                        supportLanguage
                ))
                .orElse(new BaseReponse(
                        HttpStatus.NOT_FOUND.value(),
                        HttpStatus.NOT_FOUND.getReasonPhrase(),
                        null
                ));
    }

    @PostMapping
    public BaseReponse createSupportLanguage(@Valid @RequestBody SupportLanguages supportLanguage) {
        return new BaseReponse(
                HttpStatus.OK.value(),
                HttpStatus.OK.getReasonPhrase(),
                supportLanguageService.createSupportLanguage(supportLanguage)
        );
    }

    @PostMapping("/{id}")
    public BaseReponse updateSupportLanguage(@Valid @PathVariable int id, @Valid @RequestBody SupportLanguages supportLanguage) {
        return supportLanguageService.updateSupportLanguage(id, supportLanguage)
                .map(updatedSupportLanguage -> {
                    return new BaseReponse(
                            HttpStatus.OK.value(),
                            HttpStatus.OK.getReasonPhrase(),
                            updatedSupportLanguage
                         );
                })
                .orElseGet(() ->new BaseReponse(
                        HttpStatus.NOT_FOUND.value(),
                        HttpStatus.NOT_FOUND.getReasonPhrase(),
                        null
                ));
    }

    @DeleteMapping("/{id}")
    public BaseReponse deleteSupportLanguage(@Valid @PathVariable int id) {
        if (supportLanguageService.deleteSupportLanguage(id)) {
            return new BaseReponse(
                    HttpStatus.NO_CONTENT.value(),
                    HttpStatus.NO_CONTENT.getReasonPhrase(),
                    null
            );
        }
        return new BaseReponse(
                HttpStatus.NOT_FOUND.value(),
                HttpStatus.NOT_FOUND.getReasonPhrase(),
                null
        );
    }
}
