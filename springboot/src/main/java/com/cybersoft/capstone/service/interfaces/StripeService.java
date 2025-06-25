package com.cybersoft.capstone.service.interfaces;

import com.cybersoft.capstone.dto.OrderDTO;
import com.cybersoft.capstone.payload.request.CheckoutRequest;
import com.cybersoft.capstone.payload.response.StripeResponse;

import org.springframework.stereotype.Service;

@Service
public interface StripeService {
    StripeResponse checkout(int cartId, int userID, CheckoutRequest req);
    OrderDTO fulfillCheckout(String sessionId, int userId, int cartId);
}

