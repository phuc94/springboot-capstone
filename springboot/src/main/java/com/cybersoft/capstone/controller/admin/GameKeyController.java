package com.cybersoft.capstone.controller.admin;

import java.util.List;

import jakarta.validation.Valid;

import com.cybersoft.capstone.entity.GameKey;
import com.cybersoft.capstone.payload.response.BaseResponse;
import com.cybersoft.capstone.service.interfaces.GameKeyService;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin/game_key")
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
