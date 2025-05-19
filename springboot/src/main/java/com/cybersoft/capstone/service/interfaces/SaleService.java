package com.cybersoft.capstone.service.interfaces;

import com.cybersoft.capstone.dto.AdminSaleDTO;
import jakarta.validation.Valid;

import java.util.List;

public interface SaleService {
    public List<AdminSaleDTO> getAllSales();
    public AdminSaleDTO getSalesById(int id);
    public AdminSaleDTO createSales(@Valid AdminSaleDTO adminSaleDTO);
    public AdminSaleDTO updateSales(int id, AdminSaleDTO adminSaleDTO);
    public void deleteSalesById(int id);
}
