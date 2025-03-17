package com.cybersoft.capstone.service.implement;

import com.cybersoft.capstone.entity.NoPlayers;
import com.cybersoft.capstone.repository.NoPlayerRepository;
import com.cybersoft.capstone.service.interfaces.NoPlayerService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class NoPlayerServiceImpl implements NoPlayerService {

    private final NoPlayerRepository noPlayerRepository;

    public NoPlayerServiceImpl(NoPlayerRepository noPlayerRepository) {
        this.noPlayerRepository = noPlayerRepository;
    }

    @Override
    public List<NoPlayers> getAllNoPlayers() {
        return noPlayerRepository.findAll();
    }

    @Override
    public Optional<NoPlayers> getNoPlayerById(int id) {
        return noPlayerRepository.findById(id);
    }

    @Override
    public NoPlayers createNoPlayer(NoPlayers noPlayer) {
        return noPlayerRepository.save(noPlayer);
    }

    @Override
    public Optional<NoPlayers> updateNoPlayer(int id, NoPlayers noPlayer) {
        return noPlayerRepository.findById(id)
                .map(existingNoPlayer -> {
                    existingNoPlayer.setNo_player(noPlayer.getNo_player());
                    System.out.println(noPlayer.getNo_player());
                    return noPlayerRepository.save(existingNoPlayer);
                });
    }

    @Override
    public boolean deleteNoPlayerById(int id) {
        if (noPlayerRepository.existsById(id)) {
            noPlayerRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
