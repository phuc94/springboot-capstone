package com.cybersoft.capstone.service.interfaces;

import com.cybersoft.capstone.payload.response.StripeResponse;

import org.springframework.stereotype.Service;

@Service
public interface StripeService {
    StripeResponse checkout(int cartId, int userID);
    void fulfillCheckout(String sessionId, int userId, int cartId);
}

