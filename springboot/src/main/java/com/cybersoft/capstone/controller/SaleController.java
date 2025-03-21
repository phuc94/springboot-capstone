package com.cybersoft.capstone.controller;

import com.cybersoft.capstone.entity.Sales;
import com.cybersoft.capstone.payload.response.BaseResponse;
import com.cybersoft.capstone.service.interfaces.SaleService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/sale")
public class SaleController {

    private final SaleService saleService;

    public SaleController(SaleService saleService) {
        this.saleService = saleService;
    }

    @GetMapping
    public BaseResponse<List<Sales>> getAllSales() {
        return saleService.getAllSales();
    }

    @GetMapping("/{id}")
    public BaseResponse<Sales> getSaleById(@Valid @PathVariable int id) {
        return saleService.getSalesById(id);
    }

    @PostMapping
    public BaseResponse<Sales> createSale(@Valid @RequestBody Sales sale) {
        return saleService.createSales(sale);
    }

    @PostMapping("/{id}")
    public BaseResponse<Sales> updateSale(@Valid @PathVariable int id, @Valid @RequestBody Sales sale) {
        return saleService.updateSales(id, sale);
    }

    @DeleteMapping("/{id}")
    public BaseResponse<Void> deleteSale(@Valid @PathVariable int id) {
        return saleService.deleteSalesById(id);
    }

}
