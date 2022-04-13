package flab.delideli.service.payment;

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
public class DepositStrategy implements PaymentStrategy {

	@Override
	public PaymentDTO getPaymentDTO(Long orderId, String userId, RequestPaymentDTO requestPaymentDTO) {
		return new PaymentDTO(
			orderId, userId, PaymentType.DEPOSIT,
			requestPaymentDTO.getAmountPaid(), PaymentStatus.BEFORE_CHECK, LocalDateTime.now()
		);
	}

}