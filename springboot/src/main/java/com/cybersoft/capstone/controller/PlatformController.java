package com.cybersoft.capstone.controller;

import com.cybersoft.capstone.dto.PlatformCreateDTO;
import com.cybersoft.capstone.entity.Platforms;
import com.cybersoft.capstone.payload.response.BaseResponse;
import com.cybersoft.capstone.service.interfaces.PlatformService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/platform")
public class PlatformController {
    private final PlatformService platformService;

    public PlatformController(PlatformService platformService) {
        this.platformService = platformService;
    }

    @PostMapping
    public BaseResponse<Platforms> createPlatform(@Valid @RequestBody PlatformCreateDTO platformDTO) {
        return platformService.createPlatform(platformDTO);
    }

    @GetMapping
    public BaseResponse<List<Platforms>> getAllPlatforms(@RequestParam(required = false) Boolean isOrphan) {
        return platformService.getAllPlatforms(isOrphan);
    }

    @GetMapping("/{id}")
    public BaseResponse<Platforms> getPlatformById(@Valid @PathVariable int id) {
        return platformService.getPlatformById(id);
    }

    @PostMapping("/{id}")
    public BaseResponse<Platforms> updatePlatform(@Valid @PathVariable int id,@Valid @RequestBody Platforms platform) {
        return platformService.updatePlatform(id, platform);
    }

    @DeleteMapping("/{id}")
    public BaseResponse<Void> deletePlatform(@Valid @PathVariable int id) {
        return platformService.deletePlatform(id);
    }

}
