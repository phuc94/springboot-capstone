package com.cybersoft.capstone.service.implement;

import com.cybersoft.capstone.entity.NoPlayers;
import com.cybersoft.capstone.exception.NotFoundException;
import com.cybersoft.capstone.payload.response.AcceptedResponse;
import com.cybersoft.capstone.payload.response.BaseResponse;
import com.cybersoft.capstone.payload.response.OkResponse;
import com.cybersoft.capstone.repository.NoPlayerRepository;
import com.cybersoft.capstone.service.interfaces.NoPlayerService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NoPlayerServiceImpl implements NoPlayerService {

    private final NoPlayerRepository noPlayerRepository;

    public NoPlayerServiceImpl(NoPlayerRepository noPlayerRepository) {
        this.noPlayerRepository = noPlayerRepository;
    }

    @Override
    public BaseResponse<List<NoPlayers>> getAllNoPlayers() {
        return new OkResponse<>(noPlayerRepository.findAll());
    }

    @Override
    public BaseResponse<NoPlayers> getNoPlayerById(int id) {
        return noPlayerRepository.findById(id)
                .map(OkResponse::new)
                .orElseThrow(() -> new NotFoundException(HttpStatus.NOT_FOUND.getReasonPhrase()));
    }

    @Override
    public BaseResponse<NoPlayers> createNoPlayer(NoPlayers noPlayer) {
        return new OkResponse<>(noPlayerRepository.save(noPlayer));
    }

    @Override
    public BaseResponse<NoPlayers> updateNoPlayer(int id, NoPlayers noPlayer) {
        return noPlayerRepository.findById(id)
                .map(foundNoPlayer -> {
                    foundNoPlayer.setNo_player(noPlayer.getNo_player());
                    return new OkResponse<>(noPlayerRepository.save(foundNoPlayer));
                })
                .orElseThrow(() -> new NotFoundException(HttpStatus.NOT_FOUND.getReasonPhrase()));
    }

    @Override
    public BaseResponse<Void> deleteNoPlayerById(int id) {
        if (noPlayerRepository.existsById(id)) {
            noPlayerRepository.deleteById(id);
            return new AcceptedResponse<>();
        }
        throw new NotFoundException(HttpStatus.NOT_FOUND.getReasonPhrase());
    }
}
