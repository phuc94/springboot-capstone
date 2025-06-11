package com.cybersoft.capstone.service.implement;

import java.util.List;
import java.util.stream.Collectors;

import com.cybersoft.capstone.dto.ClientGameDTO;
import com.cybersoft.capstone.dto.GameCardDTO;
import com.cybersoft.capstone.dto.mapper.GameMapper;
import com.cybersoft.capstone.exception.NotFoundException;
import com.cybersoft.capstone.repository.GameRepository;
import com.cybersoft.capstone.service.interfaces.ClientGameService;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public class ClientGameServiceImpl implements ClientGameService {

    private final GameRepository gameRepository;
    private final GameMapper gameMapper;

    public ClientGameServiceImpl (GameRepository gameRepository, GameMapper gameMapper) {
        this.gameRepository = gameRepository;
        this.gameMapper = gameMapper;
    }

    @Override
    public List<ClientGameDTO> getAllClientGames() {
        return gameRepository.findByDeletedOnIsNull()
            .stream().map(gameMapper::toClientGameDTO).collect(Collectors.toList());
    }
    @Override
    public ClientGameDTO getClientGameById(int id) {
        return gameRepository.findByIdAndDeletedOnIsNull(id)
            .map(gameMapper::toClientGameDTO)
            .orElseThrow(() -> new NotFoundException(HttpStatus.NOT_FOUND.getReasonPhrase()));
    }

    @Override
    public List<GameCardDTO> getCardGameByPlatform(int id) {
        return gameRepository.findTop4ByOrderByCreatedAtDesc().stream()
            .map(gameMapper::toGameCardDTO).collect(Collectors.toList());
    }
}
