package com.cybersoft.capstone.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Entity
@Data
public class NoPlayers implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String no_player;

    @OneToMany(mappedBy = "noPlayers", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<GameNoPlayer> gameNoPlayers;
}
