package com.cybersoft.capstone.service.interfaces;

import com.cybersoft.capstone.entity.GameDescription;
import com.cybersoft.capstone.payload.response.BaseResponse;

import java.util.List;

public interface GameDescriptionService {
    public BaseResponse<List<GameDescription>> getAllGameDescriptions();
    public BaseResponse<GameDescription> getGameDescriptionById(int id);
    public BaseResponse<GameDescription> createGameDescription(GameDescription gameDescription);
    public BaseResponse<GameDescription> updateGameDescription(int id, GameDescription gameDescription);
    public BaseResponse<Void> deleteGameDescriptionById(int id);
}
