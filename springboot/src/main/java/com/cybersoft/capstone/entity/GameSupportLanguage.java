package com.cybersoft.capstone.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;

@Entity(name = "game_support_language")
@Data
public class GameSupportLanguage implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "game_id", referencedColumnName = "id")
    private Games game;

    @ManyToOne
    @JoinColumn(name = "language_id", referencedColumnName = "id")
    private SupportLanguages language;
}
