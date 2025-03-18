package com.cybersoft.capstone.controller;

import com.cybersoft.capstone.entity.NoPlayers;
import com.cybersoft.capstone.payload.response.BaseResponse;
import com.cybersoft.capstone.service.interfaces.NoPlayerService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/no_player")
public class NoPlayerController {

    private final NoPlayerService noPlayerService;

    public NoPlayerController(NoPlayerService noPlayerService) {
        this.noPlayerService = noPlayerService;
    }

    @GetMapping
    public BaseResponse<List<NoPlayers>> getAllNoPlayers() {
        return noPlayerService.getAllNoPlayers();
    }

    @GetMapping("/{id}")
    public BaseResponse<NoPlayers> getNoPlayerById(@Valid @PathVariable int id) {
        return noPlayerService.getNoPlayerById(id);
    }

    @PostMapping
    public BaseResponse<NoPlayers> createNoPlayer(@Valid @RequestBody NoPlayers noPlayer) {
        return noPlayerService.createNoPlayer(noPlayer);
    }

    @PostMapping("/{id}")
    public BaseResponse<NoPlayers> updateNoPlayer(@Valid @PathVariable int id, @Valid @RequestBody NoPlayers noPlayer) {
        return noPlayerService.updateNoPlayer(id, noPlayer);
    }

    @DeleteMapping("/{id}")
    public BaseResponse<Void> deleteNoPlayer(@Valid @PathVariable int id) {
        return noPlayerService.deleteNoPlayerById(id);
    }
}
