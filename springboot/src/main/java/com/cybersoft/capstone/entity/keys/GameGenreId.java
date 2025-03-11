package com.cybersoft.capstone.entity.keys;

import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;

@Embeddable
@Data
public class GameGenreId implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "genre_id")
    private int genreId;

    @Column(name = "game_id")
    private int gameId;

}
