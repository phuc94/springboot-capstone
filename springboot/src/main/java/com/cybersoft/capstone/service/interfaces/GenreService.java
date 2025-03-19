package com.cybersoft.capstone.service.interfaces;

import com.cybersoft.capstone.entity.Genres;
import com.cybersoft.capstone.payload.response.BaseResponse;

import java.util.List;

public interface GenreService {
    public BaseResponse<List<Genres>> getAllGenres();
    public BaseResponse<Genres> getGenreById(int id);
    public BaseResponse<Genres> createGenre(Genres genres);
    public BaseResponse<Genres> updateGenre(int id, Genres genres);
    public BaseResponse<Void> deleteGenreById(int id);
}
