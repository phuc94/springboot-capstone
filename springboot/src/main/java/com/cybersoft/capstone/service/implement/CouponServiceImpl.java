package com.cybersoft.capstone.service.implement;

import com.cybersoft.capstone.entity.Coupons;
import com.cybersoft.capstone.exception.NotFoundException;
import com.cybersoft.capstone.payload.response.AcceptedResponse;
import com.cybersoft.capstone.payload.response.BaseResponse;
import com.cybersoft.capstone.payload.response.OkResponse;
import com.cybersoft.capstone.repository.CouponRepository;
import com.cybersoft.capstone.service.interfaces.CouponService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CouponServiceImpl implements CouponService {

    private final CouponRepository couponRepository;

    public CouponServiceImpl(CouponRepository couponRepository) {
        this.couponRepository = couponRepository;
    }

    @Override
    public BaseResponse<List<Coupons>> getAllCoupons() {
        return new OkResponse<>(couponRepository.findAll());
    }

    @Override
    public BaseResponse<Coupons> getCouponById(int id) {
        return couponRepository.findById(id)
                .map(OkResponse::new)
                .orElseThrow(() -> new NotFoundException(HttpStatus.NOT_FOUND.getReasonPhrase()));
    }

    @Override
    public BaseResponse<Coupons> createCoupon(Coupons coupon) {
        return new OkResponse<>(couponRepository.save(coupon));
    }

    @Override
    public BaseResponse<Coupons> updateCoupon(int id, Coupons coupon) {
        return couponRepository.findById(id)
                .map(foundCoupon -> {
                    foundCoupon.setCouponType(coupon.getCouponType());
                    foundCoupon.setCode(coupon.getCode());
                    foundCoupon.setStatus(coupon.getStatus());
                    foundCoupon.setStart_date(coupon.getStart_date());
                    foundCoupon.setEnd_date(coupon.getEnd_date());
                    foundCoupon.setUsage_limit(coupon.getUsage_limit());
                    foundCoupon.setUsed_count(coupon.getUsed_count());
                    foundCoupon.setDiscount_amount(coupon.getDiscount_amount());
                    return new OkResponse<>(couponRepository.save(foundCoupon));
                })
                .orElseThrow(() -> new NotFoundException(HttpStatus.NOT_FOUND.getReasonPhrase()));
    }

    @Override
    public BaseResponse<Void> deleteCouponById(int id) {
        if (couponRepository.existsById(id)) {
            couponRepository.deleteById(id);
            return new AcceptedResponse<>();
        }
        throw new NotFoundException(HttpStatus.NOT_FOUND.getReasonPhrase());
    }
}
