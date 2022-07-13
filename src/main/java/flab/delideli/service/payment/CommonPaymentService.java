package flab.delideli.service.payment;

import flab.delideli.dao.PaymentDao;
import flab.delideli.dto.PaymentDTO;
import flab.delideli.enums.PaymentStatus;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
public class CommonPaymentService {

	private final PaymentDao paymentDao;

	public PaymentDTO getPaymentSummary(long paymentId, String userId) {
		return paymentDao.selectPaymentSummary(paymentId, userId);
	}

	@Transactional
	public void cancelPayment(long paymentId, String userId) {
		PaymentDTO dto = getPaymentSummary(paymentId, userId);
		validateUnableToCancelPayment(dto);
		validateCorrectPaymentOwner(dto, userId);
		paymentDao.updatePaymentStatusCanceled(paymentId, userId);
	}

	public long getPaymentId(long orderId) {
		return paymentDao.selectPaymentId(orderId);
	}

	private void validateUnableToCancelPayment(PaymentDTO dto) {
		if (dto.getPaymentStatus() == PaymentStatus.CONFIRMED_UNABLE_TO_CANCEL) {
			throw new IllegalStateException("결제를 취소할 수 없습니다.");
		}
	}

	private void validateCorrectPaymentOwner(PaymentDTO dto, String userId) {
		if (!userId.equals(dto.getUserId())) {
			throw new IllegalArgumentException("잘못된 입력입니다.");
		}
	}

}