package com.cybersoft.capstone.service.interfaces;

import com.cybersoft.capstone.entity.Sales;
import com.cybersoft.capstone.payload.response.BaseResponse;

import java.util.List;

public interface SaleService {
    public BaseResponse<List<Sales>> getAllSales();
    public BaseResponse<Sales> getSalesById(int id);
    public BaseResponse<Sales> createSales(Sales sales);
    public BaseResponse<Sales> updateSales(int id, Sales sales);
    public BaseResponse<Void> deleteSalesById(int id);
}
