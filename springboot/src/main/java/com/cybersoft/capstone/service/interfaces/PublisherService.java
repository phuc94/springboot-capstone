package com.cybersoft.capstone.service.interfaces;

import com.cybersoft.capstone.entity.Publishers;
import com.cybersoft.capstone.payload.response.BaseResponse;

import java.util.List;

public interface PublisherService {
    public BaseResponse<List<Publishers>> getAllPublishers();
    public BaseResponse<Publishers> getPublisherById(int id);
    public BaseResponse<Publishers> createPublisher(Publishers publishers);
    public BaseResponse<Publishers> updatePublisher(int id, Publishers publishers);
    public BaseResponse<Void> deletePublisherById(int id);
}
