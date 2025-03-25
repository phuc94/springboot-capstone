package com.cybersoft.capstone.entity;

import com.cybersoft.capstone.entity.enums.Availability;
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
    @Enumerated(EnumType.STRING)
    private Availability availability;

    // Entities mapping
    @OneToOne
    @JoinColumn(name = "description_id", referencedColumnName = "id")
    private GameDescription gameDescription;
    @ManyToOne
    @JoinColumn(name = "publisher_id", referencedColumnName = "id")
    private Publishers publisher;
    @ManyToOne
    @JoinColumn(name="developer_id", referencedColumnName = "id")
    private Developers developer;

    @OneToMany(mappedBy = "game")
    private List<GamePlayMode> gamePlayMode;

    @OneToMany(mappedBy = "game")
    private List<GameSupportLanguage> gameSupportLanguages;

    @OneToMany(mappedBy = "game")
    private List<GameGenre> gameGenres;

    @OneToMany(mappedBy = "game")
    private List<GameNoPlayer> noPlayer;

    @OneToMany(mappedBy = "game")
    private List<GameMedia> gameMedia;

    @OneToMany(mappedBy = "game")
    private List<Reviews> reviews;

    @OneToMany(mappedBy = "game")
    private List<Medias> medias;
}
