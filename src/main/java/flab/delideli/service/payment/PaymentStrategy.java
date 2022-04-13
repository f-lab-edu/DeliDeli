package flab.delideli.service.payment;

import flab.delideli.dto.PaymentDTO;
import flab.delideli.dto.RequestPaymentDTO;

public interface PaymentStrategy {

	PaymentDTO getPaymentDTO(Long orderId, String userId, RequestPaymentDTO requestPaymentDTO);

}
