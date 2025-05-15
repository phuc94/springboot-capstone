package com.cybersoft.capstone.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

import com.cybersoft.capstone.entity.enums.MediaType;

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

    private String title;

    @ManyToOne
    @JoinColumn(name = "game_id", referencedColumnName = "id")
    private Games game;

}

