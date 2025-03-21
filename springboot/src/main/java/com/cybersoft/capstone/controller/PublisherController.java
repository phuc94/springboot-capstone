package com.cybersoft.capstone.controller;

import com.cybersoft.capstone.entity.Publishers;
import com.cybersoft.capstone.payload.response.BaseResponse;
import com.cybersoft.capstone.service.interfaces.PublisherService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/publisher")
public class PublisherController {

    private final PublisherService publisherService;

    public PublisherController(PublisherService publisherService) {
        this.publisherService = publisherService;
    }

    @GetMapping
    public BaseResponse<List<Publishers>> getAllPublishers() {
        return publisherService.getAllPublishers();
    }

    @GetMapping("/{id}")
    public BaseResponse<Publishers> getPublisherById(@Valid @PathVariable int id) {
        return publisherService.getPublisherById(id);
    }

    @PostMapping
    public BaseResponse<Publishers> createPublisher(@Valid @RequestBody Publishers publisher) {
        return publisherService.createPublisher(publisher);
    }

    @PostMapping("/{id}")
    public BaseResponse<Publishers> updatePublisher(@Valid @PathVariable int id, @Valid @RequestBody Publishers publisher) {
        return publisherService.updatePublisher(id, publisher);
    }

    @DeleteMapping("/{id}")
    public BaseResponse<Void> deletePublisher(@Valid @PathVariable int id) {
        return publisherService.deletePublisherById(id);
    }

}
