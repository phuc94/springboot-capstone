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

    private String url;
    private boolean is_primary;
    private MediaType media_type;

    @ManyToOne
    @JoinColumn(name = "game_id", referencedColumnName = "id")
    private Games game;

}
