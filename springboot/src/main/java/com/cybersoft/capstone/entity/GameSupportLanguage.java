package com.cybersoft.capstone.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

import java.io.Serializable;
import jakarta.persistence.*;


@Entity
@Table(name = "game_support_language", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"support_language_id", "game_id"})
})
@Data
public class GameSupportLanguage implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @ManyToOne
    @JoinColumn(name = "support_language_id", nullable = false)
    private SupportLanguage supportLanguage;

    @ManyToOne
    @JoinColumn(name = "game_id", nullable = false)
    private Games game;
}
