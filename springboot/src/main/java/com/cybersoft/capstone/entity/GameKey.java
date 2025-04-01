package com.cybersoft.capstone.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity(name = "game_key")
public class GameKey {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(unique = true, nullable = false)
    private String key;
    @Column(nullable = false)
    private boolean active;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "game_id", referencedColumnName = "id", nullable = false)
    private Games game;

}
