package com.cybersoft.capstone.service.interfaces;

import com.cybersoft.capstone.entity.PlayModes;
import com.cybersoft.capstone.payload.response.BaseResponse;

import java.util.List;

public interface PlayModeService {
    public BaseResponse<List<PlayModes>> getAllPlayModes();

    public BaseResponse<PlayModes> getPlayModeById(int id);

    public BaseResponse<PlayModes> createPlayMode(PlayModes playMode);

    public BaseResponse<PlayModes> updatePlayMode(int id, PlayModes playMode);

    public BaseResponse<Void> deletePlayModeById(int id);
}
