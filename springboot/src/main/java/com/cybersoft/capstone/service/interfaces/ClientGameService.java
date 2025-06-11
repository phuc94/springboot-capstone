package com.cybersoft.capstone.service.interfaces;

import java.util.List;

import com.cybersoft.capstone.dto.ClientGameDTO;
import com.cybersoft.capstone.dto.GameCardDTO;

public interface ClientGameService {
    public List<ClientGameDTO> getAllClientGames();
    public ClientGameDTO getClientGameById(int id);
    public List<GameCardDTO> getCardGameByPlatform(int id);
}
