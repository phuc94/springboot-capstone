package com.cybersoft.capstone.controller;

import com.cybersoft.capstone.entity.Genres;
import com.cybersoft.capstone.payload.response.BaseResponse;
import com.cybersoft.capstone.service.interfaces.GenreService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/genre")
public class GenreController {

    private final GenreService genreService;

    public GenreController(GenreService genreService) {
        this.genreService = genreService;
    }

    @GetMapping
    public BaseResponse<List<Genres>> getAllGenres() {
        return genreService.getAllGenres();
    }

    @GetMapping("/{id}")
    public BaseResponse<Genres> getGenreById(@PathVariable int id) {
        return genreService.getGenreById(id);
    }

    @PostMapping
    public BaseResponse<Genres> createGenre(@Valid @RequestBody Genres genres) {
        return genreService.createGenre(genres);
    }

    @PostMapping("/{id}")
    public BaseResponse<Genres> updateGenre(@PathVariable int id, @Valid @RequestBody Genres genres) {
        return genreService.updateGenre(id, genres);
    }

    @DeleteMapping("/{id}")
    public BaseResponse<Void> deleteGenre(@PathVariable int id) {
        return genreService.deleteGenreById(id);
    }

}
