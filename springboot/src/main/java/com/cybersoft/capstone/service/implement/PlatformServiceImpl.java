package com.cybersoft.capstone.service.implement;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.cybersoft.capstone.dto.ClientPlatformDTO;
import com.cybersoft.capstone.dto.PlatformCreateDTO;
import com.cybersoft.capstone.dto.mapper.PlatformMapper;
import com.cybersoft.capstone.entity.Platforms;
import com.cybersoft.capstone.exception.NotFoundException;
import com.cybersoft.capstone.repository.PlatformRepository;
import com.cybersoft.capstone.service.interfaces.PlatformService;
import com.cybersoft.capstone.specification.PlatformSpecification;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public class PlatformServiceImpl implements PlatformService {
    private final PlatformRepository platformRepository;
    private final PlatformMapper platformMapper;

    public PlatformServiceImpl(PlatformRepository platformRepository, PlatformMapper platformMapper) {
        this.platformRepository = platformRepository;
        this.platformMapper = platformMapper;
    }

    @Override
    public Platforms createPlatform(PlatformCreateDTO platformDTO) {
        Platforms platform = platformMapper.toPlatform(platformDTO);
        if (platformDTO.parentId() != null) {
            Platforms parentPlatform = platformRepository.findById(platformDTO.parentId())
                    .orElseThrow(() -> new NotFoundException(HttpStatus.NOT_FOUND.getReasonPhrase()));
            platform.setParent(parentPlatform);
        }
        return platformRepository.save(platform);
    }

    @Override
    public List<Platforms> getAllPlatforms(Boolean isOrphan) {
        Specification<Platforms> spec = Specification.where(PlatformSpecification.isOrphan(isOrphan));
        return platformRepository.findAll(spec);
    }

    @Override
    @Cacheable("clientPlatform")
    public List<ClientPlatformDTO> getAllClientPlatforms() {
        List<Platforms> platforms = platformRepository.findAll();
        HashMap<Integer, ClientPlatformDTO> dtoMap = new HashMap<>();
        for (Platforms platform : platforms) {
            ClientPlatformDTO dto = new ClientPlatformDTO();
            dto.setId(platform.getId());
            dto.setName(platform.getName());
            dto.setTitle(platform.getTitle());
            dto.setChildren(new ArrayList<>());
            dtoMap.put(Integer.valueOf(dto.getId()), dto);
        }

        List<ClientPlatformDTO> rootPlatforms = new ArrayList<>();
        for (Platforms platform : platforms) {
            ClientPlatformDTO dto = dtoMap.get(platform.getId());
            if (platform.getParent() != null) {
                ClientPlatformDTO parentDto = dtoMap.get(platform.getParent().getId());
                parentDto.getChildren().add(dto);
            } else {
                rootPlatforms.add(dto);
            }
        }
        return rootPlatforms;
    }

    @Override
    public Platforms getPlatformById(int id) {
        return platformRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(HttpStatus.NOT_FOUND.getReasonPhrase()));
    }

    @Override
    public Platforms updatePlatform(int id, Platforms platform) {
        return platformRepository.findById(id)
                .map(foundPlatform-> {
                    foundPlatform.setName(platform.getName());
                    foundPlatform.setTitle(platform.getTitle());
                    if(platform.getParent() != null) {
                        Platforms parentPlatform = platformRepository.findById(platform.getParent().getId())
                                .orElseThrow(() -> new NotFoundException(HttpStatus.NOT_FOUND.getReasonPhrase()));
                        foundPlatform.setParent(parentPlatform);
                    }
                    return platformRepository.save(foundPlatform);
                })
                .orElseThrow(() -> new NotFoundException(HttpStatus.NOT_FOUND.getReasonPhrase()));
    }

    @Override
    public Void deletePlatform(int id) {
        if (platformRepository.existsById(id)) {
            platformRepository.deleteById(id);
        }
        throw new NotFoundException(HttpStatus.NOT_FOUND.getReasonPhrase());
    }
}
