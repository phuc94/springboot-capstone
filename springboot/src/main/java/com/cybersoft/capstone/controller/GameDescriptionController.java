package com.cybersoft.capstone.controller;

import com.cybersoft.capstone.entity.GameDescription;
import com.cybersoft.capstone.payload.response.BaseResponse;
import com.cybersoft.capstone.repository.GameDescriptionRepository;
import com.cybersoft.capstone.service.interfaces.GameDescriptionService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/game_description")
public class GameDescriptionController {

    private final GameDescriptionService gameDescriptionService;

    public GameDescriptionController(GameDescriptionService gameDescriptionService) {
        this.gameDescriptionService = gameDescriptionService;
    }

    @GetMapping
    public BaseResponse<List<GameDescription>> getAllGameDescription() {
        return gameDescriptionService.getAllGameDescriptions();
    }

    @GetMapping("/{id}")
    public BaseResponse<GameDescription> getGameDescription(@Valid @PathVariable int id) {
        return gameDescriptionService.getGameDescriptionById(id);
    }

    @PostMapping
    public BaseResponse<GameDescription> createGameDescription(@Valid @RequestBody GameDescription gameDescription) {
        return gameDescriptionService.createGameDescription(gameDescription);
    }

    @PostMapping("/{id}")
    public BaseResponse<GameDescription> updateGameDescription(@Valid @PathVariable int id, @Valid @RequestBody GameDescription gameDescription) {
        return gameDescriptionService.updateGameDescription(id, gameDescription);
    }

    @DeleteMapping("/{id}")
    public BaseResponse<Void> deleteGameDescription(@Valid @PathVariable int id) {
        return gameDescriptionService.deleteGameDescriptionById(id);
    }

}
