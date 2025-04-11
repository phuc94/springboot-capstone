package com.cybersoft.capstone.service.implement;

import com.cybersoft.capstone.entity.GameDescription;
import com.cybersoft.capstone.exception.NotFoundException;
import com.cybersoft.capstone.payload.response.AcceptedResponse;
import com.cybersoft.capstone.payload.response.BaseResponse;
import com.cybersoft.capstone.payload.response.OkResponse;
import com.cybersoft.capstone.repository.GameDescriptionRepository;
import com.cybersoft.capstone.service.interfaces.GameDescriptionService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class GameDescriptionServiceImpl implements GameDescriptionService {

    private final GameDescriptionRepository gameDescriptionRepository;

    public GameDescriptionServiceImpl(GameDescriptionRepository gameDescriptionRepository) {
        this.gameDescriptionRepository = gameDescriptionRepository;
    }

    @Override
    public BaseResponse<List<GameDescription>> getAllGameDescriptions() {
        return new OkResponse<>(gameDescriptionRepository.findAll());
    }

    @Override
    public BaseResponse<GameDescription> getGameDescriptionById(int id) {
        return gameDescriptionRepository.findById(id)
                .map(OkResponse::new)
                .orElseThrow(() -> new NotFoundException(HttpStatus.NOT_FOUND.getReasonPhrase()));
    }

    @Override
    @Transactional
    public BaseResponse<GameDescription> createGameDescription(GameDescription gameDescription) {
        return new OkResponse<>(gameDescriptionRepository.save(gameDescription));
    }

    @Override
    public BaseResponse<GameDescription> updateGameDescription(int id, GameDescription gameDescription) {
        return gameDescriptionRepository.findById(id)
                .map(foundGameDescription -> {
                    foundGameDescription.setDescription(gameDescription.getDescription());
                    return new OkResponse<>(gameDescriptionRepository.save(foundGameDescription));
                })
                .orElseThrow(() -> new NotFoundException(HttpStatus.NOT_FOUND.getReasonPhrase()));
    }

    @Override
    public BaseResponse<Void> deleteGameDescriptionById(int id) {
        if(gameDescriptionRepository.existsById(id)) {
            gameDescriptionRepository.deleteById(id);
            return new AcceptedResponse<>();
        }
        throw new NotFoundException(HttpStatus.NOT_FOUND.getReasonPhrase());
    }
}
