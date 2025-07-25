package com.cybersoft.capstone.service.interfaces;

import java.util.List;

import com.cybersoft.capstone.dto.ClientGameCardDTO;
import com.cybersoft.capstone.dto.ClientGameDTO;
import com.cybersoft.capstone.dto.ClientGameDetailDTO;
import com.cybersoft.capstone.entity.Games;

public interface ClientGameService {
    public List<ClientGameDetailDTO> getAllClientGames();
    public ClientGameDetailDTO getClientGameDetailById(int id);
    public ClientGameDTO getClientGameById(int id);
    public List<ClientGameCardDTO> getCardGameByPlatform(int id);
    public Games getGameById(int id);
    public Games save(Games game);
    public List<ClientGameCardDTO> getGamesByPlatformName(String platformName);
}
