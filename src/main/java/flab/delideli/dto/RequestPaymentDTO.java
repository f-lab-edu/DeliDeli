package flab.delideli.dto;

import flab.delideli.enums.PaymentType;
import javax.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class RequestPaymentDTO {

	@NotNull
	private PaymentType paymentType;
	@NotNull
	private Long amountPaid;
	@NotNull
	private Long totalPrice;

	@Builder
	public RequestPaymentDTO(PaymentType paymentType,
		Long amountPaid, Long totalPrice) {
		this.paymentType = paymentType;
		this.amountPaid = amountPaid;
		this.totalPrice = totalPrice;
	}

}