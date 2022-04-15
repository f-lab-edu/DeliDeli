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
	private long amountPaid;
	@NotNull
	private long totalPrice;

	@Builder
	public RequestPaymentDTO(PaymentType paymentType,
		long amountPaid, long totalPrice) {
		this.paymentType = paymentType;
		this.amountPaid = amountPaid;
		this.totalPrice = totalPrice;
	}

}