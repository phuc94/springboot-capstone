package com.cybersoft.capstone.service.implement;

import com.cybersoft.capstone.entity.Publishers;
import com.cybersoft.capstone.exception.NotFoundException;
import com.cybersoft.capstone.payload.response.AcceptedResponse;
import com.cybersoft.capstone.payload.response.BaseResponse;
import com.cybersoft.capstone.payload.response.OkResponse;
import com.cybersoft.capstone.repository.PublisherRepository;
import com.cybersoft.capstone.service.interfaces.PublisherService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PublisherServiceImpl implements PublisherService {

    private final PublisherRepository publisherRepository;

    public PublisherServiceImpl(PublisherRepository publisherRepository) {
        this.publisherRepository = publisherRepository;
    }

    @Override
    public BaseResponse<List<Publishers>> getAllPublishers() {
        return new OkResponse<>(publisherRepository.findAll());
    }

    @Override
    public BaseResponse<Publishers> getPublisherById(int id) {
        return publisherRepository.findById(id)
                .map(OkResponse::new)
                .orElseThrow(() -> new NotFoundException(HttpStatus.NOT_FOUND.getReasonPhrase()));
    }

    @Override
    public BaseResponse<Publishers> createPublisher(Publishers publishers) {
        return new OkResponse<>(publisherRepository.save(publishers));
    }

    @Override
    public BaseResponse<Publishers> updatePublisher(int id, Publishers publishers) {
        return publisherRepository.findById(id)
                .map(foundPublisher -> {
                    foundPublisher.setName(publishers.getName());
                    return new OkResponse<>(publisherRepository.save(foundPublisher));
                })
                .orElseThrow(() -> new NotFoundException(HttpStatus.NOT_FOUND.getReasonPhrase()));
    }

    @Override
    public BaseResponse<Void> deletePublisherById(int id) {
        if (publisherRepository.existsById(id)) {
            publisherRepository.deleteById(id);
            return new AcceptedResponse<>();
        }
        throw new NotFoundException(HttpStatus.NOT_FOUND.getReasonPhrase());
    }
}
