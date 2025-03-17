package com.cybersoft.capstone.service.interfaces;

import com.cybersoft.capstone.entity.NoPlayers;

import java.util.List;
import java.util.Optional;

public interface NoPlayerService {
    public List<NoPlayers> getAllNoPlayers();

    public Optional<NoPlayers> getNoPlayerById(int id);

    public NoPlayers createNoPlayer(NoPlayers noPlayer);

    public Optional<NoPlayers> updateNoPlayer(int id, NoPlayers noPlayer);

    public boolean deleteNoPlayerById(int id);
}