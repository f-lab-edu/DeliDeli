package flab.delideli.service;

import flab.delideli.dto.RequestPaymentDTO;

public interface PaymentService {

	void pay(Long orderId, String userId, RequestPaymentDTO requestPaymentDTO);

}
