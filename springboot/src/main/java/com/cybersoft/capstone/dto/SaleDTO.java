package com.cybersoft.capstone.dto;

import java.sql.Timestamp;

import lombok.Data;

@Data
public class SaleDTO {
    private int id;
    private Timestamp startDate;
    private Timestamp endDate;
    private Integer amount;
}

