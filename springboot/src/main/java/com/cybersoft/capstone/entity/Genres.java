package com.cybersoft.capstone.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity(name = "genres")
@Data
public class Genres {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String genre;

    @OneToMany(mappedBy = "genres")
    private List<GameGenre> gameGenres;
}
