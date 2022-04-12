package flab.delideli.service.payment;

import flab.delideli.dao.PaymentDao;
import flab.delideli.dto.PaymentDTO;
import flab.delideli.dto.RequestPaymentDTO;
import flab.delideli.enums.PaymentStatus;
import flab.delideli.enums.PaymentType;
import flab.delideli.exception.PaymentFailureException;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class DepositService implements PaymentService {

	private final PaymentDao paymentDao;

	@Override
	public void pay(Long orderId, String userId, RequestPaymentDTO requestPaymentDTO) {

		if (!requestPaymentDTO.getAmountPaid().equals(requestPaymentDTO.getTotalPrice())) {
			throw new PaymentFailureException("결제 금액을 다시 확인해주세요.");
		}

		PaymentDTO paymentDTO = new PaymentDTO(
			orderId, userId, PaymentType.DEPOSIT,
			requestPaymentDTO.getAmountPaid(), PaymentStatus.BEFORE_CHECK, LocalDateTime.now()
		);

		paymentDao.insertPayment(paymentDTO);

	}

}