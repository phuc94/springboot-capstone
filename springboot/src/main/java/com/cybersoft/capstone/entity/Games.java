package com.cybersoft.capstone.entity;

import com.cybersoft.capstone.entity.enums.EsrbRating;
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
    // private int size;
    private Timestamp releaseDate;

    // Enum
    @Enumerated(EnumType.STRING)
    private EsrbRating esrbRating;

    // Entities mapping
    private Games gameDlcId;
    private GamePlayMode gamePlayMode;

    @ManyToOne
    @JoinColumn(name = "description_id")
    private GameDescription gameDescription;

    private GameSupportLanguage gameSupportLanguage;

    @OneToMany(mappedBy = "games")
    private List<GameGenre> gameGenres;

    private Medias media;
    @ManyToOne
    @JoinColumn(name = "publisher_id", referencedColumnName = "id")
    private Publishers publisher;
    private Developers developer;
    private NoPlayers noPlayer;

    @OneToOne(mappedBy = "game")
    private GameMedia gameMedia;
}
