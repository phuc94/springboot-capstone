package com.cybersoft.capstone.entity;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Entity(name = "games")
@Data
public class Games implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String title;
    private Integer price;
    private int stock;
    private int avgRating;
    private int ratingCount;
    @Column(name = "deleted_on")
    private Timestamp deletedOn;
    private Timestamp createdAt;
    private Timestamp updatedAt;

    // Entities mapping
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "description_id", referencedColumnName = "id")
    private GameDescription gameDescription;

    @OneToOne
    @JoinColumn(name="platform_id", referencedColumnName = "id", nullable = false)
    private Platforms platform;

    @OneToMany(mappedBy = "game")
    @JsonIgnore
    private List<GameKey> gameKeys;

    @OneToMany(mappedBy = "game")
    private List<Reviews> reviews;

    @OneToMany(mappedBy = "game")
    private List<Medias> medias;

    @OneToOne
    @JoinColumn(name="sale_id", referencedColumnName = "id")
    private Sales sale;

}

