package com.cybersoft.capstone.entity;

import com.cybersoft.capstone.entity.enums.OrderStatus;
import com.cybersoft.capstone.entity.enums.PaymentMethodStatus;
import jakarta.persistence.*;
import lombok.Data;
import jakarta.persistence.OneToMany;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import java.io.Serializable;
import java.util.Date;


@Entity
@Data
public class Orders implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Enumerated(EnumType.STRING)
    private PaymentMethodStatus paymentStatus;

    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus;

    private int originalAmount;
    private int discountedAmount;
    private int totalAmount;

    @Column(name = "deleted_on")
    private Timestamp deletedOn;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<OrderItem> orderItems;

    @ManyToOne
    @JoinColumn(name="payment_method_id", referencedColumnName = "id")
    private PaymentMethod paymentMethod;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private Users users;
}
