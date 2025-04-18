package com.cybersoft.capstone.service.implement;

import com.cybersoft.capstone.dto.AdminCouponDTO;
import com.cybersoft.capstone.dto.mapper.CouponMapper;
import com.cybersoft.capstone.exception.NotFoundException;
import com.cybersoft.capstone.repository.CouponRepository;
import com.cybersoft.capstone.service.interfaces.CouponService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CouponServiceImpl implements CouponService {

    private final CouponRepository couponRepository;
    private final CouponMapper couponMapper;

    public CouponServiceImpl(CouponRepository couponRepository, CouponMapper couponMapper) {
        this.couponRepository = couponRepository;
        this.couponMapper = couponMapper;
    }

    @Override
    public List<AdminCouponDTO> getAllCoupons() {
        return couponRepository.findAll()
                .stream().map(couponMapper::toAdminCouponDTO).collect(Collectors.toList());
    }

    @Override
    public AdminCouponDTO getCouponById(int id) {
        return couponRepository.findById(id)
                .map(couponMapper::toAdminCouponDTO)
                .orElseThrow(() -> new NotFoundException(HttpStatus.NOT_FOUND.getReasonPhrase()));
    }

    @Override
    public AdminCouponDTO createCoupon(AdminCouponDTO couponDTO) {
        return couponMapper.toAdminCouponDTO(couponRepository.save(couponMapper.toCoupons(couponDTO)));
    }

    @Override
    public AdminCouponDTO updateCoupon(int id, AdminCouponDTO couponDTO) {
        return couponRepository.findById(id)
                .map(foundCoupon -> {
//                    foundCoupon.setCouponType(couponDTO.getCouponType());
                    foundCoupon.setCode(couponDTO.getCode());
                    foundCoupon.setStatus(couponDTO.getStatus());
                    foundCoupon.setStartDate(couponDTO.getStartDate());
                    foundCoupon.setEndDate(couponDTO.getEndDate());
                    foundCoupon.setUsageLimit(couponDTO.getUsageLimit());
                    foundCoupon.setUsedCount(couponDTO.getUsedCount());
//                    foundCoupon.setDiscountAmount(couponDTO.getUsageLimit());
                    return couponMapper.toAdminCouponDTO(couponRepository.save(foundCoupon));
                })
                .orElseThrow(() -> new NotFoundException(HttpStatus.NOT_FOUND.getReasonPhrase()));
    }

    @Override
    public void deleteCouponById(int id) {
        if (couponRepository.existsById(id)) {
            couponRepository.deleteById(id);
            return;
        }
        throw new NotFoundException(HttpStatus.NOT_FOUND.getReasonPhrase());
    }
}
