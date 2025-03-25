package com.cybersoft.capstone.entity;

import com.cybersoft.capstone.entity.enums.MediaType;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Medias {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "url", nullable = false)
    private String url;
    @Column(name = "is_primary", nullable = false)
    private boolean primary = false;
    @Column(name = "media_type", nullable = false)
    private MediaType media_type;

    @ManyToOne
    @JoinColumn(name = "game_id", referencedColumnName = "id")
    private Games game;

}
