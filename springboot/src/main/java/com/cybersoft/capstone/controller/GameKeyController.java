package com.cybersoft.capstone.controller;

import com.cybersoft.capstone.entity.GameKey;
import com.cybersoft.capstone.payload.response.BaseResponse;
import com.cybersoft.capstone.service.interfaces.GameKeyService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/game_key")
public class GameKeyController {
    private final GameKeyService gameKeyService;

    public GameKeyController(GameKeyService gameKeyService) {
        this.gameKeyService = gameKeyService;
    }

    @PostMapping
    public BaseResponse<GameKey> createGameKey(@Valid @RequestBody GameKey gameKey) {
        return gameKeyService.createGameKey(gameKey);
    }

    @GetMapping
    public BaseResponse<List<GameKey>> getAllGameKey() {
        return gameKeyService.getAllGameKeys();
    }

    @GetMapping("/{id}")
    public BaseResponse<GameKey> getGameKeyById(@Valid @PathVariable int id) {
        return gameKeyService.getGameKeyById(id);
    }

    @PostMapping("/{id}")
    public BaseResponse<GameKey> updateGameKey(@Valid @PathVariable int id,@Valid @RequestBody GameKey gameKey) {
        return gameKeyService.updateGameKey(id, gameKey);
    }

    @DeleteMapping("/{id}")
    public BaseResponse<Void> deleteGameKey(@Valid @PathVariable int id) {
        return gameKeyService.deleteGameKey(id);
    }
}
