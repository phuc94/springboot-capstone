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
    private String gameName;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "no_player_id")
    private NoPlayers noPlayers;
} 