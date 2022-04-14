package flab.delideli.service.payment;

import flab.delideli.dao.PaymentDao;
import flab.delideli.dto.PaymentDTO;
import flab.delideli.dto.RequestPaymentDTO;
import flab.delideli.enums.PaymentStatus;
import flab.delideli.enums.PaymentType;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class KakaoPayService implements PaymentService {

	private final PaymentDao paymentDao;

	@Override
	public void pay(Long orderId, String userId, RequestPaymentDTO requestPaymentDTO) {

		PaymentDTO paymentDTO = new PaymentDTO(
			orderId, userId, PaymentType.KAKAO_PAY,
			requestPaymentDTO.getAmountPaid(), PaymentStatus.BEFORE_CHECK, LocalDateTime.now()
		);

		paymentDao.insertPayment(paymentDTO);

	}

}