package com.cybersoft.capstone.service.implement;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.cybersoft.capstone.dto.ClientGameCardDTO;
import com.cybersoft.capstone.dto.ClientGameDTO;
import com.cybersoft.capstone.dto.ClientGameDetailDTO;
import com.cybersoft.capstone.dto.mapper.GameMapper;
import com.cybersoft.capstone.entity.Games;
import com.cybersoft.capstone.entity.Platforms;
import com.cybersoft.capstone.exception.NotFoundException;
import com.cybersoft.capstone.repository.GameRepository;
import com.cybersoft.capstone.repository.PlatformRepository;
import com.cybersoft.capstone.service.interfaces.ClientGameService;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public class ClientGameServiceImpl implements ClientGameService {

    private final GameRepository gameRepository;
    private final GameMapper gameMapper;
    private final PlatformRepository platformRepository;

    public ClientGameServiceImpl (
        GameRepository gameRepository,
        GameMapper gameMapper,
        PlatformRepository platformRepository
        ) {
        this.gameRepository = gameRepository;
        this.gameMapper = gameMapper;
        this.platformRepository = platformRepository;
    }

    @Override
    public List<ClientGameDetailDTO> getAllClientGames() {
        return gameRepository.findByDeletedOnIsNull()
            .stream().map(gameMapper::toClientGameDetailDTO).collect(Collectors.toList());
    }
    @Override
    public ClientGameDetailDTO getClientGameDetailById(int id) {
        return gameRepository.findByIdAndDeletedOnIsNull(id)
            .map(gameMapper::toClientGameDetailDTO)
            .orElseThrow(() -> new NotFoundException(HttpStatus.NOT_FOUND.getReasonPhrase()));
    }

    @Override
    public List<ClientGameCardDTO> getCardGameByPlatform(int id) {
        return gameRepository.findTop4ByPlatformIdAndStockGreaterThanOrderByUpdatedAtDesc(id, 0).stream()
            .map(gameMapper::toClientGameCardDTO).collect(Collectors.toList());
    }

    @Override
    public ClientGameDTO getClientGameById(int id) {
        return gameRepository.findByIdAndDeletedOnIsNull(id)
            .map(gameMapper::toClientGameDTO)
            .orElseThrow(() -> new NotFoundException(HttpStatus.NOT_FOUND.getReasonPhrase()));
    }

    @Override
    public Games getGameById(int id) {
        return gameRepository.findByIdAndDeletedOnIsNull(id)
            .orElseThrow(() -> new NotFoundException(HttpStatus.NOT_FOUND.getReasonPhrase()));
    }

    @Override
    public Games save(Games game) {
        return gameRepository.save(game);
    }

    @Override
    public List<ClientGameCardDTO> getGamesByPlatformName(String platformName) {
        Platforms platform = platformRepository.findByName(platformName);
        if (platform.getParent() == null) {
            List<Platforms> childPlatforms = platformRepository.findByParentId(platform.getId());
            List<Integer> platformIds = new ArrayList<Integer>();
            platformIds.add(platform.getId());
            childPlatforms.forEach(childPlatform -> {platformIds.add(childPlatform.getId());});
            return gameRepository.findByPlatform_IdInAndDeletedOnIsNull(platformIds).stream()
                .map(gameMapper::toClientGameCardDTO).collect(Collectors.toList());
        }
        return gameRepository.findByPlatform_NameAndDeletedOnIsNull(platformName)
                .stream().map(gameMapper::toClientGameCardDTO).collect(Collectors.toList());
    }
}
