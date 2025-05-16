// package com.cybersoft.capstone.entity;
//
// import jakarta.persistence.*;
// import lombok.Data;
//
// @Entity
// @Data
// public class Guests {
//     @Id
//     @GeneratedValue(strategy = GenerationType.IDENTITY)
//     private int id;
//     private String sessionId;
//
//     @OneToOne(mappedBy = "guest")
//     private Carts cart;
// }
