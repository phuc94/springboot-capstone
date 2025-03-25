package com.cybersoft.capstone.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;

@Entity
@Data
public class GameNoPlayer implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name="game_id", referencedColumnName = "id")
    private Games game;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "no_player_id", referencedColumnName = "id")
    private NoPlayers noPlayers;
} 