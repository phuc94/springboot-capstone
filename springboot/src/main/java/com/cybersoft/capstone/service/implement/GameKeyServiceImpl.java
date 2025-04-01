package com.cybersoft.capstone.service.implement;

import com.cybersoft.capstone.entity.GameKey;
import com.cybersoft.capstone.exception.NotFoundException;
import com.cybersoft.capstone.payload.response.AcceptedResponse;
import com.cybersoft.capstone.payload.response.BaseResponse;
import com.cybersoft.capstone.payload.response.OkResponse;
import com.cybersoft.capstone.repository.GameKeyRepository;
import com.cybersoft.capstone.service.interfaces.GameKeyService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

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
                    foundGameKey.setActive(gameKey.isActive());
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
}
