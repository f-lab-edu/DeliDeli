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
public class CreditCardService implements PaymentService {

	private final PaymentDao paymentDao;

	@Override
	public void pay(long orderId, String userId, RequestPaymentDTO requestPaymentDTO) {

		if (requestPaymentDTO.getCreditCardCorp() == null
			&& requestPaymentDTO.getCreditCardNumber() == null) {
			throw new IllegalArgumentException("카드 정보가 누락되었습니다.");
		}

		PaymentDTO paymentDTO = PaymentDTO.builder()
			.orderId(orderId)
			.userId(userId)
			.paymentType(PaymentType.CREDIT_CARD)
			.amountPaid(requestPaymentDTO.getAmountPaid())
			.paymentStatus(PaymentStatus.CONFIRMED)
			.paymentDate(LocalDateTime.now())
			.creditCardCorp(requestPaymentDTO.getCreditCardCorp())
			.creditCardNumber(requestPaymentDTO.getCreditCardNumber())
			.build();

		paymentDao.insertPayment(paymentDTO);

	}

}