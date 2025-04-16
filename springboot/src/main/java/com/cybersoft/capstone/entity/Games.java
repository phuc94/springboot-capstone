package com.cybersoft.capstone.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

@Entity(name = "games")
@Data
public class Games implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String title;
    private Integer price;
    private boolean isDlc;
    private int keyCount;
    private Timestamp releaseDate;

    // Entities mapping
    @OneToOne
    @JoinColumn(name = "description_id", referencedColumnName = "id")
    private GameDescription gameDescription;

    @OneToMany(mappedBy = "game")
    private List<GameKey> gameKeys;

    @OneToMany(mappedBy = "game")
    private List<Reviews> reviews;

    @OneToMany(mappedBy = "game")
    private List<Medias> medias;
}
