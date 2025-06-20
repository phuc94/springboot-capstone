package com.cybersoft.capstone.service.interfaces;

import java.util.List;

import com.cybersoft.capstone.entity.Medias;
import com.cybersoft.capstone.payload.response.BaseResponse;

import org.springframework.stereotype.Service;

@Service
public interface MediaService {
    public Medias createMedia(Medias media);
    public BaseResponse<List<Medias>> getAllMedia();
    public BaseResponse<Medias> getMediaById(int id);
    public BaseResponse<Medias> updateMedia(int id, Medias media);
    public BaseResponse<Void> deleteMedia(int id);
}
