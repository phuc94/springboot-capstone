package com.cybersoft.capstone.service.interfaces;

import java.util.List;

import com.cybersoft.capstone.entity.GameKey;
import com.cybersoft.capstone.payload.response.BaseResponse;

public interface GameKeyService {
    public BaseResponse<GameKey> createGameKey(GameKey gameKey);
    public BaseResponse<List<GameKey>> createGameKeyBatch(List<GameKey> gameKeys);
    public BaseResponse<GameKey> getGameKeyById(int gameKeyId);
    public BaseResponse<List<GameKey>> getAllGameKeys();
    public BaseResponse<GameKey> updateGameKey(int gameKeyId, GameKey gameKey);
    public BaseResponse<Void> deleteGameKey(int gameKeyId);
    public GameKey getAvailableGameKey(int gameId);
    public List<GameKey> findTopNByGamesIdAndActivatedIsFalse(int gameId, int n);
    public List<GameKey> saveAll(List<GameKey> gameKeys);
}
