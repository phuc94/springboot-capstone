package com.cybersoft.capstone.dto;

import java.sql.Timestamp;

import com.cybersoft.capstone.entity.enums.SaleStatus;

import lombok.Data;

@Data
public class AdminSaleDTO {
    private int id;
    private Timestamp startDate;
    private Timestamp endDate;
    private int amount;
    private SaleStatus status;
}
