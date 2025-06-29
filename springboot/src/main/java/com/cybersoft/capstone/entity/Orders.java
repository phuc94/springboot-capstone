package com.cybersoft.capstone.entity;

import java.io.Serializable;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

import com.cybersoft.capstone.entity.enums.PaymentMethodStatus;

import lombok.Data;

@Entity
@Data
public class Orders implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Enumerated(EnumType.STRING)
    private PaymentMethodStatus paymentStatus;

    // @Enumerated(EnumType.STRING)
    // private OrderStatus orderStatus;
    private String orderStatus;

    private String sessionId ;
    private String url ;
    private String email ;
    private String name ;
    private String phone ;
    private String note ;
    private int originalAmount;
    private int subTotalAmount;
    private int discountAmount;
    private int totalAmount;

    @Column(name = "deleted_on")
    private Timestamp deletedOn;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    @OneToMany(mappedBy = "order", fetch = FetchType.EAGER)
    private List<OrderItem> orderItems;

    @ManyToOne
    @JoinColumn(name="payment_method_id", referencedColumnName = "id")
    private PaymentMethod paymentMethod;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private Users users;
}
