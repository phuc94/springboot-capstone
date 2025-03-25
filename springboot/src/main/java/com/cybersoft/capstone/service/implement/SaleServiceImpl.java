package com.cybersoft.capstone.service.implement;

import com.cybersoft.capstone.entity.Sales;
import com.cybersoft.capstone.exception.NotFoundException;
import com.cybersoft.capstone.payload.response.AcceptedResponse;
import com.cybersoft.capstone.payload.response.BaseResponse;
import com.cybersoft.capstone.payload.response.OkResponse;
import com.cybersoft.capstone.repository.SaleRepository;
import com.cybersoft.capstone.service.interfaces.SaleService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SaleServiceImpl implements SaleService {

    private final SaleRepository saleRepository;

    public SaleServiceImpl(SaleRepository saleRepository) {
        this.saleRepository = saleRepository;
    }

    @Override
    public BaseResponse<List<Sales>> getAllSales() {
        return new OkResponse<>(saleRepository.findAll());
    }

    @Override
    public BaseResponse<Sales> getSalesById(int id) {
        return saleRepository.findById(id)
                .map(OkResponse::new)
                .orElseThrow(() -> new NotFoundException(HttpStatus.NOT_FOUND.getReasonPhrase()));
    }

    @Override
    public BaseResponse<Sales> createSales(Sales sales) {
        return new OkResponse<>(saleRepository.save(sales));
    }

    @Override
    public BaseResponse<Sales> updateSales(int id, Sales sales) {
        return saleRepository.findById(id)
                .map(foundSale -> {
                    foundSale.setStartDate(sales.getStartDate());
                    foundSale.setEndDate(sales.getEndDate());
                    foundSale.setAmount(sales.getAmount());
                    return new OkResponse<>(saleRepository.save(foundSale));
                })
                .orElseThrow(() -> new NotFoundException(HttpStatus.NOT_FOUND.getReasonPhrase()));
    }

    @Override
    public BaseResponse<Void> deleteSalesById(int id) {
        if (saleRepository.existsById(id)) {
            saleRepository.deleteById(id);
            return new AcceptedResponse<>();
        }
        throw new NotFoundException(HttpStatus.NOT_FOUND.getReasonPhrase());
    }

}
