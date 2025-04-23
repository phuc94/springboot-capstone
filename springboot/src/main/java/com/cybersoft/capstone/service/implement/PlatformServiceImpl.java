package com.cybersoft.capstone.service.implement;

import com.cybersoft.capstone.entity.Platforms;
import com.cybersoft.capstone.exception.NotFoundException;
import com.cybersoft.capstone.payload.response.AcceptedResponse;
import com.cybersoft.capstone.payload.response.BaseResponse;
import com.cybersoft.capstone.payload.response.OkResponse;
import com.cybersoft.capstone.repository.PlatformRepository;
import com.cybersoft.capstone.service.interfaces.PlatformService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PlatformServiceImpl implements PlatformService {
    private final PlatformRepository platformRepository;

    public PlatformServiceImpl(PlatformRepository platformRepository) {
        this.platformRepository = platformRepository;
    }

    @Override
    public BaseResponse<Platforms> createPlatform(Platforms platform) {
        return new OkResponse<>(platformRepository.save(platform));
    }

    @Override
    public BaseResponse<List<Platforms>> getAllPlatforms() {
        return new OkResponse<>(platformRepository.findAll());
    }

    @Override
    public BaseResponse<Platforms> getPlatformById(int id) {
        return platformRepository.findById(id)
                .map(OkResponse::new)
                .orElseThrow(() -> new NotFoundException(HttpStatus.NOT_FOUND.getReasonPhrase()));
    }

    @Override
    public BaseResponse<Platforms> updatePlatform(int id, Platforms platform) {
        return platformRepository.findById(id)
                .map(foundPlatform-> {
                    foundPlatform.setName(platform.getName());
                    return new OkResponse<>(platformRepository.save(foundPlatform));
                })
                .orElseThrow(() -> new NotFoundException(HttpStatus.NOT_FOUND.getReasonPhrase()));
    }

    @Override
    public BaseResponse<Void> deletePlatform(int id) {
        if (platformRepository.existsById(id)) {
            platformRepository.deleteById(id);
            return new AcceptedResponse<>();
        }
        throw new NotFoundException(HttpStatus.NOT_FOUND.getReasonPhrase());
    }
}
