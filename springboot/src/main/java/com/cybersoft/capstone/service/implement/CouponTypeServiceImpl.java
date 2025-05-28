package com.cybersoft.capstone.service.implement;

import com.cybersoft.capstone.dto.CouponTypeDTO;
import com.cybersoft.capstone.dto.mapper.CouponTypeMapper;
import com.cybersoft.capstone.entity.CouponTypes;
import com.cybersoft.capstone.exception.NotFoundException;
import com.cybersoft.capstone.repository.CouponTypeRepository;
import com.cybersoft.capstone.service.interfaces.CouponTypeService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CouponTypeServiceImpl implements CouponTypeService {

    private final CouponTypeRepository couponTypeRepository;
    private final CouponTypeMapper couponTypeMapper;

    public CouponTypeServiceImpl(CouponTypeRepository couponTypeRepository, CouponTypeMapper couponTypeMapper) {
        this.couponTypeRepository = couponTypeRepository;
        this.couponTypeMapper = couponTypeMapper;
    }

    @Override
    public List<CouponTypeDTO> getAllCouponTypes() {
        return couponTypeRepository.findAll()
                .stream().map(couponTypeMapper::toCouponTypeDTO).collect(Collectors.toList());
    }

    @Override
    public CouponTypeDTO getCouponTypeById(int id) {
        return couponTypeRepository.findById(id)
                .map(couponTypeMapper::toCouponTypeDTO)
                .orElseThrow(() -> new NotFoundException(HttpStatus.NOT_FOUND.getReasonPhrase()));
    }

    @Override
    public CouponTypeDTO createCouponType(CouponTypeDTO couponTypeDTO) {
        return couponTypeMapper.toCouponTypeDTO(couponTypeRepository.save(couponTypeMapper.toCouponType(couponTypeDTO)));
    }

    @Override
    public CouponTypeDTO updateCouponType(int id, CouponTypeDTO couponTypeDTO) {
        return couponTypeRepository.findById(id)
                .map(foundCouponType -> {
                    foundCouponType.setType(couponTypeDTO.getType());
                    return couponTypeMapper.toCouponTypeDTO(couponTypeRepository.save(foundCouponType));
                })
                .orElseThrow(() -> new NotFoundException(HttpStatus.NOT_FOUND.getReasonPhrase()));
    }

    @Override
    public void deleteCouponTypeById(int id) {
        if(couponTypeRepository.existsById(id)){
            couponTypeRepository.deleteById(id);
            return;
        }
        throw new NotFoundException(HttpStatus.NOT_FOUND.getReasonPhrase());
    }
}
