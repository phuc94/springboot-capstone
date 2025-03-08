package com.cybersoft.capstone.entity;

import com.cybersoft.capstone.entity.enums.EsrbRating;
import jakarta.persistence.*;
import lombok.Data;

import java.sql.Timestamp;

@Entity
@Data
public class Games {
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
    private GameDescription gameDescription;
    private GameSupportLanguage gameSupportLanguage;
    private GameGenre gameGenre;
    private Medias media;
    private Publishers publisher;
    private Developers developer;
    private NoPlayers noPlayer;

}
