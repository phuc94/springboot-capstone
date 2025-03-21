package com.cybersoft.capstone.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;

@Entity(name = "game_description")
@Data
public class GameDescription implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    private String description;

    @OneToOne(mappedBy = "gameDescription")
    private Games games;
}
