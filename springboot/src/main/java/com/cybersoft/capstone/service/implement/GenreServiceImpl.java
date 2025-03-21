package com.cybersoft.capstone.service.implement;

import com.cybersoft.capstone.entity.Genres;
import com.cybersoft.capstone.exception.NotFoundException;
import com.cybersoft.capstone.payload.response.AcceptedResponse;
import com.cybersoft.capstone.payload.response.BaseResponse;
import com.cybersoft.capstone.payload.response.OkResponse;
import com.cybersoft.capstone.repository.GenreRepository;
import com.cybersoft.capstone.service.interfaces.GenreService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GenreServiceImpl implements GenreService {

    private final GenreRepository genreRepository;

    public GenreServiceImpl(GenreRepository genreRepository) {
        this.genreRepository = genreRepository;
    }

    @Override
    public BaseResponse<List<Genres>> getAllGenres() {
        return new OkResponse<>(genreRepository.findAll());
    }

    @Override
    public BaseResponse<Genres> getGenreById(int id) {
        return genreRepository.findById(id)
                .map(OkResponse::new)
                .orElseThrow(() -> new NotFoundException(HttpStatus.NOT_FOUND.getReasonPhrase()));
    }

    @Override
    public BaseResponse<Genres> createGenre(Genres genres) {
        return new OkResponse<>(genreRepository.save(genres));
    }

    @Override
    public BaseResponse<Genres> updateGenre(int id, Genres genres) {
        return genreRepository.findById(id)
                .map(foundGenre -> {
                    foundGenre.setGenre(genres.getGenre());
                    return new OkResponse<>(genreRepository.save(foundGenre));
                })
                .orElseThrow(() -> new NotFoundException(HttpStatus.NOT_FOUND.getReasonPhrase()));
    }

    @Override
    public BaseResponse<Void> deleteGenreById(int id) {
        if(genreRepository.existsById(id)) {
            genreRepository.deleteById(id);
            return new AcceptedResponse<>();
        }
        throw new NotFoundException(HttpStatus.NOT_FOUND.getReasonPhrase());
    }
}
