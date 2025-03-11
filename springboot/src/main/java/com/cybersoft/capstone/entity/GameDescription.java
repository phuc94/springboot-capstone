package com.cybersoft.capstone.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Entity(name = "game_description")
@Data
public class GameDescription implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String description;

    @OneToMany(mappedBy = "gameDescription")
    private List<Games> games;

}
