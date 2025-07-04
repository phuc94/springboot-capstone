package com.cybersoft.capstone.service.implement;

import java.util.List;

import com.cybersoft.capstone.entity.GameKey;
import com.cybersoft.capstone.exception.NotFoundException;
import com.cybersoft.capstone.payload.response.AcceptedResponse;
import com.cybersoft.capstone.payload.response.BaseResponse;
import com.cybersoft.capstone.payload.response.OkResponse;
import com.cybersoft.capstone.repository.GameKeyRepository;
import com.cybersoft.capstone.service.interfaces.GameKeyService;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public class GameKeyServiceImpl implements GameKeyService {

    private final GameKeyRepository gameKeyRepository;

    public GameKeyServiceImpl(GameKeyRepository gameKeyRepository) {
        this.gameKeyRepository = gameKeyRepository;
    }

    @Override
    public BaseResponse<GameKey> createGameKey(GameKey gameKey) {
        return new OkResponse<>(gameKeyRepository.save(gameKey));
    }

    @Override
    public BaseResponse<List<GameKey>> createGameKeyBatch(List<GameKey> gameKeys) {
        return new OkResponse<>(gameKeyRepository.saveAll(gameKeys));
    }

    @Override
    public BaseResponse<GameKey> getGameKeyById(int gameKeyId) {
        return gameKeyRepository.findById(gameKeyId)
                .map(OkResponse::new)
                .orElseThrow(() -> new NotFoundException(HttpStatus.NOT_FOUND.getReasonPhrase()));
    }

    @Override
    public BaseResponse<List<GameKey>> getAllGameKeys() {
        return new OkResponse<>(gameKeyRepository.findAll());
    }

    @Override
    public BaseResponse<GameKey> updateGameKey(int gameKeyId, GameKey gameKey) {
        return gameKeyRepository.findById(gameKeyId)
                .map(foundGameKey -> {
                    foundGameKey.setActivated(gameKey.isActivated());
                    return new OkResponse<>(gameKeyRepository.save(foundGameKey));
                })
                .orElseThrow(() -> new NotFoundException(HttpStatus.NOT_FOUND.getReasonPhrase()));
    }

    @Override
    public BaseResponse<Void> deleteGameKey(int gameKeyId) {
        if(gameKeyRepository.existsById(gameKeyId)) {
            gameKeyRepository.deleteById(gameKeyId);
            return new AcceptedResponse<>();
        }
        throw new NotFoundException(HttpStatus.NOT_FOUND.getReasonPhrase());
    }

    @Override
    public GameKey getAvailableGameKey(int gameId) {
        return gameKeyRepository.findFirst1ByGameIdAndActivatedFalse(gameId)
                .orElseThrow(() -> new NotFoundException(HttpStatus.NOT_FOUND.getReasonPhrase()));
    }

    @Override
    public List<GameKey> findTopNByGamesIdAndActivatedIsFalse(int gameId, int n) {
        return gameKeyRepository.findTopNByGameIdAndActivatedIsFalse(gameId, n);
    }

    @Override
    public List<GameKey> saveAll(List<GameKey> gameKeys) {
        return gameKeyRepository.saveAll(gameKeys);
    }
}
