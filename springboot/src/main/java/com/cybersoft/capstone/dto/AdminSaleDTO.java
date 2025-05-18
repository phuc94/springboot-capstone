package com.cybersoft.capstone.dto;

import com.cybersoft.capstone.entity.enums.SaleStatus;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Data;

import java.sql.Timestamp;

@Data
public class AdminSaleDTO {
    private Timestamp startDate;
    private Timestamp endDate;
    private int amount;
    private SaleStatus status;
}
