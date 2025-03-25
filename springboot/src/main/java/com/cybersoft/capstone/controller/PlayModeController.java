package com.cybersoft.capstone.controller;

import com.cybersoft.capstone.entity.PlayModes;
import com.cybersoft.capstone.payload.response.BaseResponse;
import com.cybersoft.capstone.service.interfaces.PlayModeService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/no_player")
public class PlayModeController {
    private final PlayModeService playModeService;

    public PlayModeController(PlayModeService playModeService) {
        this.playModeService = playModeService;
    }

    @GetMapping
    public BaseResponse<List<PlayModes>> getAllPlayModes() {
        return playModeService.getAllPlayModes();
    }

    @GetMapping("/{id}")
    public BaseResponse<PlayModes> getNoPlayerById(@Valid @PathVariable int id) {
        return playModeService.getPlayModeById(id);
    }

    @PostMapping
    public BaseResponse<PlayModes> createNoPlayer(@Valid @RequestBody PlayModes noPlayer) {
        return playModeService.createPlayMode(noPlayer);
    }

    @PostMapping("/{id}")
    public BaseResponse<PlayModes> updateNoPlayer(@Valid @PathVariable int id, @Valid @RequestBody PlayModes noPlayer) {
        return playModeService.updatePlayMode(id, noPlayer);
    }

    @DeleteMapping("/{id}")
    public BaseResponse<Void> deleteNoPlayer(@Valid @PathVariable int id) {
        return playModeService.deletePlayModeById(id);
    }
}
