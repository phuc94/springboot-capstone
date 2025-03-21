package com.cybersoft.capstone.service.implement;

import com.cybersoft.capstone.entity.Developers;
import com.cybersoft.capstone.exception.NotFoundException;
import com.cybersoft.capstone.payload.response.AcceptedResponse;
import com.cybersoft.capstone.payload.response.BaseResponse;
import com.cybersoft.capstone.payload.response.OkResponse;
import com.cybersoft.capstone.repository.DeveloperRepository;
import com.cybersoft.capstone.service.interfaces.DeveloperService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DeveloperServiceImpl implements DeveloperService {

    private final DeveloperRepository developerRepository;

    public DeveloperServiceImpl(DeveloperRepository developerRepository) {
        this.developerRepository = developerRepository;
    }

    @Override
    public BaseResponse<List<Developers>> getAllDevelopers() {
        return new OkResponse<>(developerRepository.findAll());
    }

    @Override
    public BaseResponse<Developers> getDeveloperById(int id) {
        return developerRepository.findById(id)
                .map(OkResponse::new)
                .orElseThrow(() -> new NotFoundException(HttpStatus.NOT_FOUND.getReasonPhrase()));
    }

    @Override
    public BaseResponse<Developers> createDeveloper(Developers developer) {
        return new OkResponse<>(developerRepository.save(developer));
    }

    @Override
    public BaseResponse<Developers> updateDeveloper(int id, Developers developer) {
        return developerRepository.findById(id)
                .map(foundDeveloper -> {
                    foundDeveloper.setName(developer.getName());
                    return new OkResponse<>(developerRepository.save(foundDeveloper));
                })
                .orElseThrow(() -> new NotFoundException(HttpStatus.NOT_FOUND.getReasonPhrase()));
    }

    @Override
    public BaseResponse<Void> deleteDeveloperById(int id) {
        if(developerRepository.existsById(id)) {
            developerRepository.deleteById(id);
            return new AcceptedResponse<>();
        }
        throw new NotFoundException(HttpStatus.NOT_FOUND.getReasonPhrase());
    }
}
