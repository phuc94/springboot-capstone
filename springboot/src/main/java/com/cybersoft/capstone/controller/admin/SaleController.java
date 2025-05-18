package com.cybersoft.capstone.controller.admin;

import java.util.List;

import com.cybersoft.capstone.dto.AdminSaleDTO;
import com.cybersoft.capstone.dto.CartDTO;
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
@RequestMapping("/admin/sale")
public class SaleController {

    private final SaleService saleService;

    public SaleController(SaleService saleService) {
        this.saleService = saleService;
    }

    @GetMapping
    public BaseResponse<List<AdminSaleDTO>> getAllSales() {
        List<AdminSaleDTO> saleDTOList = saleService.getAllSales();
        BaseResponse<List<AdminSaleDTO>> response = new BaseResponse<>(200, "Lấy danh sách sale thành công");
        response.setData(saleDTOList);
        return response;
    }

    @GetMapping("/{id}")
    public BaseResponse<AdminSaleDTO> getSaleById(@Valid @PathVariable int id) {
        AdminSaleDTO saleDTO = saleService.getSalesById(id);
        BaseResponse<AdminSaleDTO> response = new BaseResponse<>(200, "Lấy sale theo ID thành công");
        response.setData(saleDTO);
        return response;
    }

    @PostMapping
    public BaseResponse<AdminSaleDTO> createSale(@Valid @RequestBody AdminSaleDTO adminSaleDTO) {
        AdminSaleDTO createSale = saleService.createSales(adminSaleDTO);
        BaseResponse<AdminSaleDTO> response = new BaseResponse<>(200, "Thêm mới sale thành công");
        response.setData(createSale);
        return response;
    }

    @PostMapping("/{id}")
    public BaseResponse<AdminSaleDTO> updateSale(@Valid @PathVariable int id, @Valid @RequestBody AdminSaleDTO adminSaleDTO) {
        AdminSaleDTO updateSale = saleService.updateSales(id, adminSaleDTO);
        BaseResponse<AdminSaleDTO> response = new BaseResponse<>(200, "Update sale thành công");
        response.setData(updateSale);
        return response;
    }

    @DeleteMapping("/{id}")
    public BaseResponse<Void> deleteSaleById(@Valid @PathVariable int id) {
        saleService.deleteSalesById(id);
        return new BaseResponse<>(200, "Xóa sale thành công");
    }

}
