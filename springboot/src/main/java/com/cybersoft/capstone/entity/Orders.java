package com.cybersoft.capstone.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.JoinColumn;
import lombok.Data;
import jakarta.persistence.OneToMany;
import jakarta.persistence.CascadeType;
import jakarta.persistence.FetchType;
import java.util.List;
import java.io.Serializable;
import java.util.Date;


@Entity
@Data
public class Orders implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private Date orderDate;
    private String status;
    private double totalAmount;
    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<OrderItem> orderItems;

    @ManyToOne
    @JoinColumn(name = "coupon_type_id", referencedColumnName = "id")
    private CouponTypes couponType;

    @ManyToOne
    @JoinColumn(name="payment_method_id", referencedColumnName = "id")
    private PaymentMethod paymentMethod;
}
