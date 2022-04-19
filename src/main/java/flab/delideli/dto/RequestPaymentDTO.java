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

	private String creditCardCorp;

	private String creditCardNumber;

	private String depositor;

	private String accountNumber;

	private String depositBank;

	@Builder
	public RequestPaymentDTO(PaymentType paymentType, long amountPaid, long totalPrice,
		String creditCardCorp, String creditCardNumber, String depositor,
		String accountNumber, String depositBank) {
		this.paymentType = paymentType;
		this.amountPaid = amountPaid;
		this.totalPrice = totalPrice;
		this.creditCardCorp = creditCardCorp;
		this.creditCardNumber = creditCardNumber;
		this.depositor = depositor;
		this.accountNumber = accountNumber;
		this.depositBank = depositBank;
	}

}