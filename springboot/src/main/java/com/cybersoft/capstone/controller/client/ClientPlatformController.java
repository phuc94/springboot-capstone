package com.cybersoft.capstone.controller.client;

import java.util.List;

import com.cybersoft.capstone.dto.ClientGameCardDTO;
import com.cybersoft.capstone.dto.ClientPlatformDTO;
import com.cybersoft.capstone.exception.NotFoundException;
import com.cybersoft.capstone.payload.response.BaseResponse;
import com.cybersoft.capstone.payload.response.OkResponse;
import com.cybersoft.capstone.service.interfaces.ClientGameService;
import com.cybersoft.capstone.service.interfaces.PlatformService;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/platform")
public class ClientPlatformController {

    private final ClientGameService clientGameService;
    private final PlatformService platformService;

    public ClientPlatformController(ClientGameService clientGameService, PlatformService platformService) {
        this.clientGameService = clientGameService;
        this.platformService = platformService;
    }

    @GetMapping("/{platformName}")
    public BaseResponse<List<ClientGameCardDTO>> getGamesByPlatform(@PathVariable String platformName) {
        try {
            return new OkResponse<>(clientGameService.getGamesByPlatformName(platformName));
        } catch (NotFoundException ex) {
            throw new NotFoundException("Games for platform not found");
        }
    }

    @GetMapping
    public BaseResponse<List<ClientPlatformDTO>> getPlatforms() {
        return new OkResponse<List<ClientPlatformDTO>>(platformService.getAllClientPlatforms());
    }

}

