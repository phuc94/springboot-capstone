package com.cybersoft.capstone.service.implement;

import com.cybersoft.capstone.dto.AdminGameDTO;
import com.cybersoft.capstone.dto.mapper.GameMapper;
import com.cybersoft.capstone.entity.GameDescription;
import com.cybersoft.capstone.entity.Games;
import com.cybersoft.capstone.entity.Platforms;
import com.cybersoft.capstone.exception.NotFoundException;
import com.cybersoft.capstone.repository.GameDescriptionRepository;
import com.cybersoft.capstone.repository.GameRepository;
import com.cybersoft.capstone.repository.PlatformRepository;
import com.cybersoft.capstone.service.interfaces.AdminGameService;
import jakarta.transaction.Transactional;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AdminGameServiceImpl implements AdminGameService {

    private final GameRepository gameRepository;
    private final GameDescriptionRepository gameDescriptionRepository;
    private final PlatformRepository platformRepository;
    private final GameMapper gameMapper;

    public AdminGameServiceImpl(GameRepository gameRepository, GameDescriptionRepository gameDescriptionRepository, PlatformRepository platformRepository, GameMapper gameMapper) {
        this.gameRepository = gameRepository;
        this.gameDescriptionRepository = gameDescriptionRepository;
        this.platformRepository = platformRepository;
        this.gameMapper = gameMapper;
    }

    @Override
    public List<AdminGameDTO> getAllAdminGames() {
        return gameRepository.findAll()
                .stream().map(gameMapper::toAdminGameDTO).collect(Collectors.toList());
    }

    @Override
    public AdminGameDTO getAdminGameById(int id) {
        return gameRepository.findById(id)
                .map(gameMapper::toAdminGameDTO)
                .orElseThrow(() -> new NotFoundException(HttpStatus.NOT_FOUND.getReasonPhrase()));
    }

    @Transactional
    @Override
    public AdminGameDTO createAdminGame(AdminGameDTO adminGameDTO) {
        GameDescription gameDescription = gameDescriptionRepository.findById(adminGameDTO.getDescriptionId()).orElse(null);
        Platforms platform = platformRepository.findById(adminGameDTO.getPlatformId())
                .orElseThrow(() -> new RuntimeException("Platform not found"));
        Games games = gameMapper.toGames(adminGameDTO, platform, gameDescription);
        return gameMapper.toAdminGameDTO(gameRepository.save(games));
    }

    @Transactional
    @Override
    public AdminGameDTO updateAdminGame(int id, AdminGameDTO adminGameDTO) {
        Games game = gameRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Game not found"));
        Platforms platform = platformRepository.findById(adminGameDTO.getPlatformId())
                .orElseThrow(() -> new RuntimeException("Platform not found"));
        GameDescription gameDescription = gameDescriptionRepository.findById(adminGameDTO.getDescriptionId())
                .orElse(null);

        game.setTitle(adminGameDTO.getTitle());
        game.setPrice(adminGameDTO.getPrice());
        game.setDlc(adminGameDTO.isDlc());
        game.setKeyCount(adminGameDTO.getKeyCount());
        game.setReleaseDate(adminGameDTO.getReleaseDate());
        game.setPlatform(platform);
        game.setGameDescription(gameDescription);

        return gameMapper.toAdminGameDTO(gameRepository.save(game));
    }

    @Transactional
    @Override
    public void deleteAdminGameById(int id) {
        if(gameRepository.existsById(id)) {
            gameRepository.deleteById(id);
            return;
        }
        throw new NotFoundException(HttpStatus.NOT_FOUND.getReasonPhrase());
    }
}
