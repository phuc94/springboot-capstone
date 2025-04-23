package com.cybersoft.capstone.controller;


import com.cybersoft.capstone.entity.Medias;
import com.cybersoft.capstone.payload.response.BaseResponse;
import com.cybersoft.capstone.service.interfaces.MediaService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/media")
public class MediaController {
    private final MediaService mediaService;

    public MediaController(MediaService mediaService) {
        this.mediaService = mediaService;
    }

    @PostMapping
    public BaseResponse<Medias> createMedia(@Valid @RequestBody Medias media) {
        return mediaService.createMedia(media);
    }

    @GetMapping
    public BaseResponse<List<Medias>> getAllMedias() {
        return mediaService.getAllMedia();
    }

    @GetMapping("/{id}")
    public BaseResponse<Medias> getMediaById(@Valid @PathVariable int id) {
        return mediaService.getMediaById(id);
    }

    @PostMapping("/{id}")
    public BaseResponse<Medias> updateMedia(@Valid @PathVariable int id, @Valid @RequestBody Medias media) {
        return mediaService.updateMedia(id, media);
    }

    @DeleteMapping("/{id}")
    public BaseResponse<Void> deleteMedia(@Valid @PathVariable int id) {
        return mediaService.deleteMedia(id);
    }

}
