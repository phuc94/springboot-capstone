package com.cybersoft.capstone.service.interfaces;

import com.cybersoft.capstone.entity.NoPlayers;
import com.cybersoft.capstone.payload.response.BaseResponse;

import java.util.List;

public interface NoPlayerService {
    public BaseResponse<List<NoPlayers>> getAllNoPlayers();

    public BaseResponse<NoPlayers> getNoPlayerById(int id);

    public BaseResponse<NoPlayers> createNoPlayer(NoPlayers noPlayer);

    public BaseResponse<NoPlayers> updateNoPlayer(int id, NoPlayers noPlayer);

    public BaseResponse<Void> deleteNoPlayerById(int id);
}