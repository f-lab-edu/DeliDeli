package flab.delideli.service.payment;

import flab.delideli.dto.PaymentDTO;
import flab.delideli.dto.RequestPaymentDTO;
import flab.delideli.enums.PaymentStatus;
import flab.delideli.enums.PaymentType;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ContactPaymentStrategy implements PaymentStrategy {

	@Override
	public PaymentDTO getPaymentDTO(Long orderId, String userId,
		RequestPaymentDTO requestPaymentDTO) {
		return new PaymentDTO(
			orderId, userId, PaymentType.CONTACT_PAYMENT,
			requestPaymentDTO.getAmountPaid(), PaymentStatus.CONFIRMED, LocalDateTime.now()
		);
	}

}