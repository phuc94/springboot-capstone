package com.cybersoft.capstone.service.implement;

import java.util.List;

import com.cybersoft.capstone.entity.Medias;
import com.cybersoft.capstone.exception.NotFoundException;
import com.cybersoft.capstone.payload.response.AcceptedResponse;
import com.cybersoft.capstone.payload.response.BaseResponse;
import com.cybersoft.capstone.payload.response.OkResponse;
import com.cybersoft.capstone.repository.MediaRepository;
import com.cybersoft.capstone.service.interfaces.MediaService;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public class MediaServiceImpl implements MediaService {
    private final MediaRepository mediaRepository;

    public MediaServiceImpl(MediaRepository mediaRepository) {
        this.mediaRepository = mediaRepository;
    }

    @Override
    public BaseResponse<Medias> createMedia(Medias media) {
        return new OkResponse<>(mediaRepository.save(media));
    }

    @Override
    public BaseResponse<List<Medias>> getAllMedia() {
        return new OkResponse<>(mediaRepository.findAll());
    }

    @Override
    public BaseResponse<Medias> getMediaById(int id) {
        return mediaRepository.findById(id)
                .map(OkResponse::new)
                .orElseThrow(() -> new NotFoundException(HttpStatus.NOT_FOUND.getReasonPhrase()));
    }

    @Override
    public BaseResponse<Medias> updateMedia(int id, Medias media) {
        return mediaRepository.findById(id)
                .map(foundMedia -> {
                    foundMedia.setMedia_type(media.getMedia_type());
                    foundMedia.setUrl(media.getUrl());
                    foundMedia.setPrimary(media.isPrimary());
                    // foundMedia.setTitle(media.getTitle());
                    return new OkResponse<>(mediaRepository.save(foundMedia));
                })
                .orElseThrow(() -> new NotFoundException(HttpStatus.NOT_FOUND.getReasonPhrase()));
    }

    @Override
    public BaseResponse<Void> deleteMedia(int id) {
        if (mediaRepository.existsById(id)) {
            mediaRepository.deleteById(id);
            return new AcceptedResponse<>();
        }
        throw new NotFoundException(HttpStatus.NOT_FOUND.getReasonPhrase());
    }
}
