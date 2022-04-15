package flab.delideli.service.payment;

import flab.delideli.dao.PaymentDao;
import flab.delideli.dto.PaymentDTO;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CommonPaymentService {

	private final PaymentDao paymentDao;

	public PaymentDTO getPaymentSummary(long paymentId, String userId) {
		return paymentDao.selectPaymentSummary(paymentId, userId);
	}

	public List<Long> getAllPaymentSummariesOfUser(String userId) {
		return paymentDao.selectAllPaymentSummariesOfUser(userId);
	}

	public void cancelPayment(long paymentId, String userId) {
		paymentDao.updatePaymentStatusCanceled(paymentId, userId);
	}

}