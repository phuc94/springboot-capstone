package com.cybersoft.capstone.service.interfaces;

import com.cybersoft.capstone.entity.Medias;
import com.cybersoft.capstone.payload.response.BaseResponse;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface MediaService {
    public BaseResponse<Medias> createMedia(Medias media);
    public BaseResponse<List<Medias>> getAllMedia();
    public BaseResponse<Medias> getMediaById(int id);
    public BaseResponse<Medias> updateMedia(int id, Medias media);
    public BaseResponse<Void> deleteMedia(int id);
}
