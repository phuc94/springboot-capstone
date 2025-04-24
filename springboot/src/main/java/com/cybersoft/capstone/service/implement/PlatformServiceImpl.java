package com.cybersoft.capstone.service.implement;

import com.cybersoft.capstone.dto.PlatformCreateDTO;
import com.cybersoft.capstone.dto.mapper.PlatformMapper;
import com.cybersoft.capstone.entity.Platforms;
import com.cybersoft.capstone.exception.NotFoundException;
import com.cybersoft.capstone.payload.response.AcceptedResponse;
import com.cybersoft.capstone.payload.response.BaseResponse;
import com.cybersoft.capstone.payload.response.OkResponse;
import com.cybersoft.capstone.repository.PlatformRepository;
import com.cybersoft.capstone.service.interfaces.PlatformService;
import com.cybersoft.capstone.specification.PlatformSpecification;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PlatformServiceImpl implements PlatformService {
    private final PlatformRepository platformRepository;
    private final PlatformMapper platformMapper;

    public PlatformServiceImpl(PlatformRepository platformRepository, PlatformMapper platformMapper) {
        this.platformRepository = platformRepository;
        this.platformMapper = platformMapper;
    }

    @Override
    public BaseResponse<Platforms> createPlatform(PlatformCreateDTO platformDTO) {
        Platforms platform = platformMapper.toPlatform(platformDTO);
        if (platformDTO.parentId() != null) {
            Platforms parentPlatform = platformRepository.findById(platformDTO.parentId())
                    .orElseThrow(() -> new NotFoundException(HttpStatus.NOT_FOUND.getReasonPhrase()));
            platform.setParent(parentPlatform);
        }
        return new OkResponse<>(platformRepository.save(platform));
    }

    @Override
    public BaseResponse<List<Platforms>> getAllPlatforms(Boolean isOrphan) {
        Specification<Platforms> spec = Specification.where(PlatformSpecification.isOrphan(isOrphan));
        return new OkResponse<>(platformRepository.findAll(spec));
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
                    foundPlatform.setTitle(platform.getTitle());
                    if(platform.getParent() != null) {
                        Platforms parentPlatform = platformRepository.findById(platform.getParent().getId())
                                .orElseThrow(() -> new NotFoundException(HttpStatus.NOT_FOUND.getReasonPhrase()));
                        foundPlatform.setParent(parentPlatform);
                    }
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
