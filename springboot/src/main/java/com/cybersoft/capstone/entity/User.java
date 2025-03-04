package com.cybersoft.capstone.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name="\"user\"")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String username;
    private String password;

}
