package com.cybersoft.capstone.controller.admin;

import java.util.List;

import jakarta.validation.Valid;

import com.cybersoft.capstone.dto.PlatformCreateDTO;
import com.cybersoft.capstone.entity.Platforms;
import com.cybersoft.capstone.payload.response.AcceptedResponse;
import com.cybersoft.capstone.payload.response.BaseResponse;
import com.cybersoft.capstone.payload.response.OkResponse;
import com.cybersoft.capstone.service.interfaces.PlatformService;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin/platform")
public class PlatformController {
    private final PlatformService platformService;

    public PlatformController(PlatformService platformService) {
        this.platformService = platformService;
    }

    @PostMapping
    public BaseResponse<Platforms> createPlatform(@Valid @RequestBody PlatformCreateDTO platformDTO) {
        return new OkResponse<Platforms>(platformService.createPlatform(platformDTO));
    }

    @GetMapping
    public BaseResponse<List<Platforms>> getAllPlatforms(@RequestParam(required = false) Boolean isOrphan) {
        return new OkResponse<List<Platforms>>(platformService.getAllPlatforms(isOrphan));
    }

    @GetMapping("/{id}")
    public BaseResponse<Platforms> getPlatformById(@Valid @PathVariable int id) {
        return new OkResponse<Platforms>(platformService.getPlatformById(id));
    }

    @PostMapping("/{id}")
    public BaseResponse<Platforms> updatePlatform(@Valid @PathVariable int id,@Valid @RequestBody Platforms platform) {
        return new OkResponse<Platforms>(platformService.updatePlatform(id, platform));
    }

    @DeleteMapping("/{id}")
    public BaseResponse<Void> deletePlatform(@Valid @PathVariable int id) {
        platformService.deletePlatform(id);
        return new AcceptedResponse<>();
    }

}
