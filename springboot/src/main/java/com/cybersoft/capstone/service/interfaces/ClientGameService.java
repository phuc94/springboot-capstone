package com.cybersoft.capstone.service.interfaces;

import java.util.List;

import com.cybersoft.capstone.dto.ClientGameDTO;

public interface ClientGameService {
    public List<ClientGameDTO> getAllClientGames();
    public ClientGameDTO getClientGameById(int id);
}
