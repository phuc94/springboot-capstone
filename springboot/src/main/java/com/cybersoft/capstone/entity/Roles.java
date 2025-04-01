package com.cybersoft.capstone.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Entity(name = "roles")
@Table(name = "roles", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"title"})
})
@Data
public class Roles implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    private String title;

    private String description;

    @OneToMany(mappedBy = "role")
    private List<Admins> admins;

}
