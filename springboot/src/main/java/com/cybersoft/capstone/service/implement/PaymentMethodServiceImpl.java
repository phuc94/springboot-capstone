package com.cybersoft.capstone.service.implement;

import java.util.List;

import com.cybersoft.capstone.entity.PaymentMethod;
import com.cybersoft.capstone.exception.NotFoundException;
import com.cybersoft.capstone.payload.response.AcceptedResponse;
import com.cybersoft.capstone.payload.response.BaseResponse;
import com.cybersoft.capstone.payload.response.OkResponse;
import com.cybersoft.capstone.repository.PaymentMethodRepository;
import com.cybersoft.capstone.service.interfaces.PaymentMethodService;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public class PaymentMethodServiceImpl implements PaymentMethodService {

    private final PaymentMethodRepository paymentMethodRepository;

    public PaymentMethodServiceImpl(PaymentMethodRepository paymentMethodRepository) {
        this.paymentMethodRepository = paymentMethodRepository;
    }

    @Override
    public BaseResponse<PaymentMethod> createPaymentMethod(PaymentMethod paymentMethod) {
        return new OkResponse<>(paymentMethodRepository.save(paymentMethod));
    }

    @Override
    public BaseResponse<PaymentMethod> updatePaymentMethod(int id, PaymentMethod paymentMethod) {
        return paymentMethodRepository.findById(id)
                .map(foundPaymentMethod-> {
                    foundPaymentMethod.setDescription(paymentMethod.getDescription());
                    foundPaymentMethod.setAccount(paymentMethod.getAccount());
                    foundPaymentMethod.setTitle(paymentMethod.getTitle());
                    foundPaymentMethod.setImage(paymentMethod.getImage());
                    return new OkResponse<>(paymentMethodRepository.save(foundPaymentMethod));
                })
                .orElseThrow(() -> new NotFoundException(HttpStatus.NOT_FOUND.getReasonPhrase()));
    }

    @Override
    public BaseResponse<PaymentMethod> getPaymentMethodById(int id) {
        return paymentMethodRepository.findById(id)
                .map(OkResponse::new)
                .orElseThrow(() -> new NotFoundException(HttpStatus.NOT_FOUND.getReasonPhrase()));
    }

    @Override
    public BaseResponse<List<PaymentMethod>> getAllPaymentMethods() {
        return new OkResponse<>(paymentMethodRepository.findAll());
    }

    @Override
    public BaseResponse<Void> deletePaymentMethodById(int id) {
        if (paymentMethodRepository.existsById(id)) {
            paymentMethodRepository.deleteById(id);
            return new AcceptedResponse<>();
        }
        throw new NotFoundException(HttpStatus.NOT_FOUND.getReasonPhrase());
    }
}
