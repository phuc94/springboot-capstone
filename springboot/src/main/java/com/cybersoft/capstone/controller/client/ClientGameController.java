package com.cybersoft.capstone.controller.client;

import java.util.List;

import com.cybersoft.capstone.dto.ClientGameDetailDTO;
import com.cybersoft.capstone.dto.GameCardDTO;
import com.cybersoft.capstone.exception.NotFoundException;
import com.cybersoft.capstone.payload.response.BaseResponse;
import com.cybersoft.capstone.payload.response.OkResponse;
import com.cybersoft.capstone.service.interfaces.ClientGameService;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/game")
public class ClientGameController {

    private final ClientGameService clientGameService;

    public ClientGameController(ClientGameService clientGameService) {
        this.clientGameService = clientGameService;
    }

    @GetMapping("/home")
    public BaseResponse<List<ClientGameDetailDTO>> getHomePageClientGames() {
        return new OkResponse<List<ClientGameDetailDTO>>(clientGameService.getAllClientGames());
    }

    @GetMapping("/{id}")
    public BaseResponse<ClientGameDetailDTO> getClientGameById(@PathVariable int id) {
        try {
          return new OkResponse<ClientGameDetailDTO>(clientGameService.getClientGameDetailById(id));
        } catch (NotFoundException ex) {
            throw new NotFoundException("Game not found");
        }
    }

    @GetMapping("/platform/{platformId}")
    public BaseResponse<List<GameCardDTO>> getGamesByPlatform(@PathVariable int platformId) {
        try {
            return new OkResponse<>(clientGameService.getGamesByPlatformId(platformId));
        } catch (NotFoundException ex) {
            throw new NotFoundException("Games for platform not found");
        }
    }
}

