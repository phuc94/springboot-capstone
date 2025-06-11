package com.cybersoft.capstone.controller.client;

import java.util.List;

import com.cybersoft.capstone.dto.PlatformSummaryDTO;
import com.cybersoft.capstone.payload.response.BaseResponse;
import com.cybersoft.capstone.payload.response.OkResponse;
import com.cybersoft.capstone.service.interfaces.ClientHomeService;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class ClientHomeController {

    private final ClientHomeService clientHomeService;

    public ClientHomeController(ClientHomeService clientHomeService) {
        this.clientHomeService = clientHomeService;
    }

    @GetMapping("/home")
    public BaseResponse<List<PlatformSummaryDTO>> getHomePageClientGames() {
        return new OkResponse<>(clientHomeService.getHomepagePlatformGames());
    }


}

