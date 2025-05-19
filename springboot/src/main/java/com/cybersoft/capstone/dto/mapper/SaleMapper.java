package com.cybersoft.capstone.dto.mapper;

import com.cybersoft.capstone.dto.AdminSaleDTO;
import com.cybersoft.capstone.entity.Sales;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface SaleMapper {
    Sales toSales(AdminSaleDTO adminSaleDTO);
    AdminSaleDTO toSalesDTO(Sales sales);
}
