package com.cybersoft.capstone.service.implement;

import com.cybersoft.capstone.entity.PlayModes;
import com.cybersoft.capstone.exception.NotFoundException;
import com.cybersoft.capstone.payload.response.AcceptedResponse;
import com.cybersoft.capstone.payload.response.BaseResponse;
import com.cybersoft.capstone.payload.response.OkResponse;
import com.cybersoft.capstone.repository.PlayModeRepository;
import com.cybersoft.capstone.service.interfaces.PlayModeService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PlayModeServiceImpl implements PlayModeService {

    private final PlayModeRepository playModeRepository;

    public PlayModeServiceImpl(PlayModeRepository playModeRepository) {
        this.playModeRepository = playModeRepository;
    }

    @Override
    public BaseResponse<List<PlayModes>> getAllPlayModes() {
        return new OkResponse<>(playModeRepository.findAll());
    }

    @Override
    public BaseResponse<PlayModes> getPlayModeById(int id) {
        return playModeRepository.findById(id)
                .map(OkResponse::new)
                .orElseThrow(() -> new NotFoundException(HttpStatus.NOT_FOUND.getReasonPhrase()));
    }

    @Override
    public BaseResponse<PlayModes> createPlayMode(PlayModes playMode) {
        return new OkResponse<>(playModeRepository.save(playMode));
    }

    @Override
    public BaseResponse<PlayModes> updatePlayMode(int id, PlayModes playMode) {
        return playModeRepository.findById(id)
                .map(foundPlayMode -> {
                    foundPlayMode.setName(playMode.getName());
                    return new OkResponse<>(playModeRepository.save(foundPlayMode));
                })
                .orElseThrow(() -> new NotFoundException(HttpStatus.NOT_FOUND.getReasonPhrase()));
    }

    @Override
    public BaseResponse<Void> deletePlayModeById(int id) {
        if (playModeRepository.existsById(id)) {
            playModeRepository.deleteById(id);
            return new AcceptedResponse<>();
        }
        throw new NotFoundException(HttpStatus.NOT_FOUND.getReasonPhrase());
    }
}
