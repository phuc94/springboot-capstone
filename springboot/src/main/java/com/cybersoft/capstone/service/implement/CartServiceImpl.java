package com.cybersoft.capstone.service.implement;

import com.cybersoft.capstone.dto.CartDTO;
import com.cybersoft.capstone.dto.mapper.CartMapper;
import com.cybersoft.capstone.exception.NotFoundException;
import com.cybersoft.capstone.repository.CartRepository;
import com.cybersoft.capstone.service.interfaces.CartService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CartServiceImpl implements CartService {

    private final CartRepository cartRepository;
    private final CartMapper cartMapper;

    public CartServiceImpl(CartRepository cartRepository, CartMapper cartMapper) {
        this.cartRepository = cartRepository;
        this.cartMapper = cartMapper;
    }

    @Override
    public List<CartDTO> getAllCarts() {
        return cartRepository.findAll()
                .stream().map(cartMapper::toCartDTO).collect(Collectors.toList());
    }

    @Override
    public CartDTO getCartById(int id) {
        return cartRepository.findById(id)
                .map(cartMapper::toCartDTO)
                .orElseThrow(() -> new NotFoundException(HttpStatus.NOT_FOUND.getReasonPhrase()));
    }

    @Override
    public CartDTO createCart(CartDTO cartDTO) {
        return cartMapper.toCartDTO(cartRepository.save(cartMapper.toCarts(cartDTO)));
    }

    @Override
    public CartDTO updateCart(int id, CartDTO cartDTO) {
        return cartRepository.findById(id)
                .map(foundCart -> {
                    foundCart.setStatus(cartDTO.getStatus());
                    return cartMapper.toCartDTO(cartRepository.save(foundCart));
                })
                .orElseThrow(() -> new NotFoundException(HttpStatus.NOT_FOUND.getReasonPhrase()));
    }

    @Override
    public void deleteCartById(int id) {
        if (cartRepository.existsById(id)) {
            cartRepository.deleteById(id);
            return;
        }
        throw new NotFoundException(HttpStatus.NOT_FOUND.getReasonPhrase());
    }
}
