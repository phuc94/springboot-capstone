package com.cybersoft.capstone.controller;

import com.cybersoft.capstone.entity.NoPlayers;
import com.cybersoft.capstone.payload.response.BaseReponse;
import com.cybersoft.capstone.service.interfaces.NoPlayerService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/no_player")
public class NoPlayerController {

    private final NoPlayerService noPlayerService;

    public NoPlayerController(NoPlayerService noPlayerService) {
        this.noPlayerService = noPlayerService;
    }

    @GetMapping
    public BaseReponse getAllNoPlayers() {
        return new BaseReponse(
            HttpStatus.OK.value(),
            HttpStatus.OK.getReasonPhrase(),
            noPlayerService.getAllNoPlayers()
        );
    }

    @GetMapping("/{id}")
    public BaseReponse getNoPlayerById(@Valid @PathVariable int id) {
        return noPlayerService.getNoPlayerById(id)
            .map(noPlayers ->
                new BaseReponse(
                    HttpStatus.OK.value(),
                    HttpStatus.OK.getReasonPhrase(),
                    noPlayers
                    )
            )
            .orElseGet(() -> new BaseReponse(
                HttpStatus.NOT_FOUND.value(),
                HttpStatus.NOT_FOUND.getReasonPhrase(),
                null
            ));
    }

    @PostMapping
    public BaseReponse createNoPlayer(@Valid @RequestBody NoPlayers noPlayer) {
        return new BaseReponse(
            HttpStatus.OK.value(),
            HttpStatus.OK.getReasonPhrase(),
            noPlayerService.createNoPlayer(noPlayer)
        );
    }

    @PostMapping("/{id}")
    public BaseReponse updateNoPlayer(@Valid @PathVariable int id, @Valid @RequestBody NoPlayers noPlayer) {
        return noPlayerService.updateNoPlayer(id, noPlayer)
            .map(updatedNoPlayer ->
                new BaseReponse(
                    HttpStatus.OK.value(),
                    HttpStatus.OK.getReasonPhrase(),
                    updatedNoPlayer
                )
            )
            .orElseGet(() -> new BaseReponse(
                HttpStatus.NOT_FOUND.value(),
                HttpStatus.NOT_FOUND.getReasonPhrase(),
                null
            ));
    }

    @DeleteMapping("/{id}")
    public BaseReponse deleteNoPlayer(@Valid @PathVariable int id) {
        if (noPlayerService.deleteNoPlayerById(id)) {
            return new BaseReponse(
                    HttpStatus.ACCEPTED.value(),
                    HttpStatus.ACCEPTED.getReasonPhrase(),
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
