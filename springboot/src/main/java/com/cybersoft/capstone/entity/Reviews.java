package com.cybersoft.capstone.entity;

import java.io.Serializable;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

import lombok.Data;

@Entity
@Data
public class Reviews implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "game_id", referencedColumnName = "id")
    private Games game;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private Users user;

    private int rating;
    private boolean recommend;
    private String comment;
}
