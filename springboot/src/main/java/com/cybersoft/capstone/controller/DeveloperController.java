package com.cybersoft.capstone.controller;

import com.cybersoft.capstone.entity.Developers;
import com.cybersoft.capstone.payload.response.BaseResponse;
import com.cybersoft.capstone.repository.DeveloperRepository;
import com.cybersoft.capstone.service.interfaces.DeveloperService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/developer")
public class DeveloperController {

    private final DeveloperService developerService;

    public DeveloperController(DeveloperService developerService) {
        this.developerService = developerService;
    }

    @GetMapping
    public BaseResponse<List<Developers>> getAllDevelopers() {
        return developerService.getAllDevelopers();
    }

    @GetMapping("/{id}")
    public BaseResponse<Developers> getDeveloperById(@Valid @PathVariable int id) {
        return developerService.getDeveloperById(id);
    }

    @PostMapping
    public BaseResponse<Developers> createDeveloper(@Valid @RequestBody Developers developer) {
        return developerService.createDeveloper(developer);
    }

    @PostMapping("/{id}")
    public BaseResponse<Developers> updateDeveloper(@Valid @PathVariable int id, @Valid @RequestBody Developers developer) {
        return developerService.updateDeveloper(id, developer);
    }

    @DeleteMapping("/{id}")
    public BaseResponse<Void> deleteDeveloper(@Valid @PathVariable int id) {
        return developerService.deleteDeveloperById(id);
    }

}
