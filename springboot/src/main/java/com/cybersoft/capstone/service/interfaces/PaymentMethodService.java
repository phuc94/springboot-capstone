package com.cybersoft.capstone.service.interfaces;

import com.cybersoft.capstone.entity.PaymentMethod;
import com.cybersoft.capstone.payload.response.BaseResponse;

import java.util.List;

public interface PaymentMethodService {
    public BaseResponse<PaymentMethod> createPaymentMethod(PaymentMethod paymentMethod);
    public BaseResponse<PaymentMethod> updatePaymentMethod(int id, PaymentMethod paymentMethod);
    public BaseResponse<PaymentMethod> getPaymentMethodById(int id);
    public BaseResponse<List<PaymentMethod>> getAllPaymentMethods();
    public BaseResponse<Void> deletePaymentMethodById(int id);
}
