package com.cybersoft.capstone.controller.admin;

import java.util.List;

import jakarta.validation.Valid;

import com.cybersoft.capstone.entity.Sales;
import com.cybersoft.capstone.payload.response.BaseResponse;
import com.cybersoft.capstone.service.interfaces.SaleService;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
