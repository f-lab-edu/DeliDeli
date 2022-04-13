package flab.delideli.service.payment;

import flab.delideli.dao.PaymentDao;
import flab.delideli.dto.RequestPaymentDTO;
import flab.delideli.exception.PaymentFailureException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class PaymentService {

	private final PaymentDao paymentDao;

	public void pay(Long orderId, String userId, RequestPaymentDTO requestPaymentDTO, PaymentStrategy paymentStrategy) {

		if (!requestPaymentDTO.getAmountPaid().equals(requestPaymentDTO.getTotalPrice())) {
			throw new PaymentFailureException("결제 금액을 다시 확인해주세요.");
		}

		paymentDao.insertPayment(paymentStrategy.getPaymentDTO(orderId, userId, requestPaymentDTO));

	}

}