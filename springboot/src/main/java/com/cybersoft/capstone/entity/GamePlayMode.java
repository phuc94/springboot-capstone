package com.cybersoft.capstone.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;

@Entity
@Data
public class GamePlayMode implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name="play_mode_id", referencedColumnName = "id", nullable = false)
    private PlayModes playmode;

    @ManyToOne
    @JoinColumn(name="game_id", referencedColumnName = "id", nullable = false)
    private Games game;
}
