package com.cybersoft.capstone.service.implement;

import com.cybersoft.capstone.dto.AdminSaleDTO;
import com.cybersoft.capstone.dto.mapper.SaleMapper;
import com.cybersoft.capstone.exception.NotFoundException;
import com.cybersoft.capstone.repository.SaleRepository;
import com.cybersoft.capstone.service.interfaces.SaleService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AdminSaleServiceImpl implements SaleService {

    private  final SaleRepository saleRepository;
    private final SaleMapper saleMapper;

    public AdminSaleServiceImpl(SaleRepository saleRepository, SaleMapper saleMapper) {
        this.saleRepository = saleRepository;
        this.saleMapper = saleMapper;
    }

    @Override
    public List<AdminSaleDTO> getAllSales() {
        return saleRepository.findAll()
                .stream().map(saleMapper::toSalesDTO).collect(Collectors.toList());
    }

    @Override
    public AdminSaleDTO getSalesById(int id) {
        return saleRepository.findById(id)
                .map(saleMapper::toSalesDTO)
                .orElseThrow(() -> new NotFoundException(HttpStatus.NOT_FOUND.getReasonPhrase()));
    }

    @Override
    public AdminSaleDTO createSales(AdminSaleDTO adminSaleDTO) {
        return saleMapper.toSalesDTO(saleRepository.save(saleMapper.toSales(adminSaleDTO)));
    }

    @Override
    public AdminSaleDTO updateSales(int id, AdminSaleDTO adminSaleDTO) {
        return saleRepository.findById(id)
                .map(foundSale -> {
                    foundSale.setAmount(adminSaleDTO.getAmount());
                    foundSale.setStatus(adminSaleDTO.getStatus());
                    foundSale.setStartDate(adminSaleDTO.getStartDate());
                    foundSale.setEndDate(adminSaleDTO.getEndDate());
                    return saleMapper.toSalesDTO(saleRepository.save(foundSale));
                })
                .orElseThrow(() -> new NotFoundException(HttpStatus.NOT_FOUND.getReasonPhrase()));
    }

    @Override
    public void deleteSalesById(int id) {
        if (saleRepository.existsById(id)) {
            saleRepository.deleteById(id);
            return;
        }
        throw new NotFoundException(HttpStatus.NOT_FOUND.getReasonPhrase());
    }
}
