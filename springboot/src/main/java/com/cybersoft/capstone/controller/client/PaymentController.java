package com.cybersoft.capstone.controller.client;

import java.util.List;

import jakarta.validation.Valid;

import com.cybersoft.capstone.entity.PaymentMethod;
import com.cybersoft.capstone.payload.response.BaseResponse;
import com.cybersoft.capstone.service.interfaces.PaymentMethodService;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/payment_method")
public class PaymentController {
    private final PaymentMethodService paymentMethodService;

    public PaymentController(PaymentMethodService paymentMethodService) {
        this.paymentMethodService = paymentMethodService;
    }

    @PostMapping
    public BaseResponse<PaymentMethod> createPaymentMethod(@Valid @RequestBody PaymentMethod paymentMethod) {
        return paymentMethodService.createPaymentMethod(paymentMethod);
    }

    @GetMapping
    public BaseResponse<List<PaymentMethod>> getAllPaymentMethod() {
        return paymentMethodService.getAllPaymentMethods();
    }

    @GetMapping("/{id}")
    public BaseResponse<PaymentMethod> getPaymentMethodById(@Valid @PathVariable int id) {
        return paymentMethodService.getPaymentMethodById(id);
    }

    @PostMapping("/{id}")
    public BaseResponse<PaymentMethod> updatePaymentMethod(@Valid @PathVariable int id,@Valid @RequestBody PaymentMethod paymentMethod) {
        return paymentMethodService.updatePaymentMethod(id, paymentMethod);
    }

    @DeleteMapping("/{id}")
    public BaseResponse<Void> deletePaymentMethod(@Valid @PathVariable int id) {
        return paymentMethodService.deletePaymentMethodById(id);
    }
}
