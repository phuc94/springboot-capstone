package com.cybersoft.capstone.service.implement;

import java.util.List;

import jakarta.validation.Valid;

import com.cybersoft.capstone.entity.CouponTypes;
import com.cybersoft.capstone.exception.NotFoundException;
import com.cybersoft.capstone.repository.CouponTypeRepository;
import com.cybersoft.capstone.service.interfaces.CouponTypeService;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public class CouponTypeServiceImpl implements CouponTypeService {

    private CouponTypeRepository couponTypeRepository;

    public CouponTypeServiceImpl (CouponTypeRepository couponTypeRepository) {
        this.couponTypeRepository = couponTypeRepository;
    }

    @Override
    public List<CouponTypes> getAllCouponTypes() {
        return couponTypeRepository.findAll();
    }

    @Override
    public CouponTypes getCouponTypeById(int id) {
        return couponTypeRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(HttpStatus.NOT_FOUND.getReasonPhrase()));
    }

    @Override
    public CouponTypes createCouponType(@Valid CouponTypes couponType) {
        return couponTypeRepository.save(couponType);
    }

    @Override
    public CouponTypes updateCouponType(int id, CouponTypes couponType) {
        return couponTypeRepository.findById(id)
                .map(foundCouponType -> {
                    foundCouponType.setType(couponType.getType());
                    return couponTypeRepository.save(foundCouponType);
                })
                .orElseThrow(() -> new NotFoundException(HttpStatus.NOT_FOUND.getReasonPhrase()));
    }

    @Override
    public void deleteCouponTypeById(int id) {
        if (couponTypeRepository.existsById(id)) {
            couponTypeRepository.deleteById(id);
            return;
        }
        throw new NotFoundException(HttpStatus.NOT_FOUND.getReasonPhrase());
    }  


}
