package flab.delideli.service.payment;

import flab.delideli.dao.PaymentDao;
import flab.delideli.dto.PaymentDTO;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CommonPaymentService {

	private final PaymentDao paymentDao;

	public PaymentDTO getPaymentSummary(long orderId, String userId) {
		return paymentDao.selectPaymentSummary(orderId, userId);
	}

}
