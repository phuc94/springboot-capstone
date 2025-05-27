package com.cybersoft.capstone.controller.client;

import java.util.List;

import com.cybersoft.capstone.dto.ClientGameDTO;
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

    @GetMapping
    public BaseResponse<List<ClientGameDTO>> getAllClientGames() {
        return new OkResponse<List<ClientGameDTO>>(clientGameService.getAllClientGames());
    }

    @GetMapping("/{id}")
    public BaseResponse<ClientGameDTO> getClientGameById(@PathVariable int id) {
        try {
          return new OkResponse<ClientGameDTO>(clientGameService.getClientGameById(id));
        } catch (NotFoundException ex) {
            throw new NotFoundException("Game not found");
        }
    }

}

