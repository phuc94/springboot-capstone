package com.cybersoft.capstone.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

import java.io.Serializable;
import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "support_language")
@Data
public class SupportLanguage implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "language", nullable = false)
    private String language;

    @OneToMany(mappedBy = "supportLanguage")
    private List<GameSupportLanguage> gameSupportLanguages;
}
