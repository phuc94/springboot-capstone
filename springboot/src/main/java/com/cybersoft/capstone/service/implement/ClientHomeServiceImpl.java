package com.cybersoft.capstone.service.implement;

import java.util.List;
import java.util.stream.Collectors;

import com.cybersoft.capstone.dto.ClientGameCardDTO;
import com.cybersoft.capstone.dto.PlatformSummaryDTO;
import com.cybersoft.capstone.service.interfaces.ClientGameService;
import com.cybersoft.capstone.service.interfaces.ClientHomeService;
import com.cybersoft.capstone.service.interfaces.PlatformService;

import org.springframework.stereotype.Service;

@Service
public class ClientHomeServiceImpl implements ClientHomeService {

    private PlatformService platformService;
    private ClientGameService clientGameService;
    
    public ClientHomeServiceImpl (PlatformService platformService, ClientGameService clientGameService) {
       this.platformService = platformService;
       this.clientGameService = clientGameService;
    }

    public List<PlatformSummaryDTO> getHomepagePlatformGames() {
        return platformService.getAllPlatforms(true).stream().map(platform -> {
            List<ClientGameCardDTO> gameCardList = clientGameService.getCardGameByPlatform(platform.getId());
            PlatformSummaryDTO platformSummary = new PlatformSummaryDTO();
            platformSummary.setName(platform.getName());
            platformSummary.setTitle(platform.getTitle());
            platformSummary.setGames(gameCardList);
            return platformSummary;
        }).collect(Collectors.toList());
    }
}
