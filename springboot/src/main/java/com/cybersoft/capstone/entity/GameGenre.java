package com.cybersoft.capstone.entity;

import com.cybersoft.capstone.entity.keys.GameGenreId;
import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;

@Entity(name = "game_genre")
@Data
public class GameGenre implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "genre_id", referencedColumnName = "id")
    private Genres genres;

    @ManyToOne
    @JoinColumn(name = "game_id", referencedColumnName = "id")
    private Games games;

}
