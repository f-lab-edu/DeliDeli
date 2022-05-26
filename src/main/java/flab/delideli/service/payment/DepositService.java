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
	public void pay(long orderId, String userId, RequestPaymentDTO requestPaymentDTO) {

		if (requestPaymentDTO.getAmountPaid() != requestPaymentDTO.getTotalPrice()) {
			throw new PaymentFailureException("결제 금액을 다시 확인해주세요.");
		}

		if (requestPaymentDTO.getDepositor() == null
			&& requestPaymentDTO.getAccountNumber() == null
			&& requestPaymentDTO.getDepositBank() == null) {
			throw new IllegalArgumentException("계좌 정보가 누락되었습니다.");
		}

		PaymentDTO paymentDTO = PaymentDTO.builder()
			.orderId(orderId)
			.userId(userId)
			.paymentType(PaymentType.DEPOSIT)
			.amountPaid(requestPaymentDTO.getAmountPaid())
			.paymentStatus(PaymentStatus.CONFIRMED)
			.paymentDate(LocalDateTime.now())
			.depositor(requestPaymentDTO.getDepositor())
			.accountNumber(requestPaymentDTO.getAccountNumber())
			.depositBank(requestPaymentDTO.getDepositBank())
			.build();

		paymentDao.insertPayment(paymentDTO);

	}

}