//package com.cybersoft.capstone.service.implement;
//
//import com.cybersoft.capstone.dto.AdminGameDTO;
//import com.cybersoft.capstone.dto.mapper.GameMapper;
//import com.cybersoft.capstone.entity.Platforms;
//import com.cybersoft.capstone.exception.NotFoundException;
//import com.cybersoft.capstone.repository.GameRepository;
//import com.cybersoft.capstone.service.interfaces.GameService;
//import org.springframework.http.HttpStatus;
//import org.springframework.stereotype.Service;
//
//import java.util.List;
//import java.util.stream.Collectors;
//
//@Service
//public class GameServiceImpl implements GameService {
//
//    private final GameRepository gameRepository;
//    private final GameMapper gameMapper;
//
//    public GameServiceImpl(GameRepository gameRepository, GameMapper gameMapper) {
//        this.gameRepository = gameRepository;
//        this.gameMapper = gameMapper;
//    }
//
//    @Override
//    public List<AdminGameDTO> getAllGames() {
//        return gameRepository.findAll()
//                .stream().map(gameMapper::toAdminGameDTO).collect(Collectors.toList());
//    }
//
//    @Override
//    public AdminGameDTO getGameById(int id) {
//        return gameRepository.findById(id)
//                .map(gameMapper::toAdminGameDTO)
//                .orElseThrow(() -> new NotFoundException(HttpStatus.NOT_FOUND.getReasonPhrase()));
//
//    }
//
//    @Override
//    public AdminGameDTO createGame(AdminGameDTO gameDTO) {
//        return gameMapper.toAdminGameDTO(gameRepository.save(gameMapper.toGames(gameDTO));
//    }
//
//    @Override
//    public AdminGameDTO updateGame(int id, AdminGameDTO gameDTO) {
//        return gameRepository.findById(id)
//                .map(foundGame -> {
//                    foundGame.setTitle(gameDTO.getTitle());
//                    foundGame.setPrice(gameDTO.getPrice());
//                    foundGame.setDlc(gameDTO.isDlc());
//                    foundGame.setKeyCount(gameDTO.getKeyCount());
//                    foundGame.setReleaseDate(gameDTO.getReleaseDate());
//                    return gameMapper.toAdminGameDTO(gameRepository.save(foundGame));
//                })
//                .orElseThrow(() -> new NotFoundException(HttpStatus.NOT_FOUND.getReasonPhrase()));
//    }
//
//    @Override
//    public void deleteGameById(int id) {
//        if (gameRepository.existsById(id)) {
//            gameRepository.deleteById(id);
//            return;
//        }
//        throw new NotFoundException(HttpStatus.NOT_FOUND.getReasonPhrase());
//    }
//}
