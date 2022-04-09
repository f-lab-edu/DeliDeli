package flab.delideli.dto;

import flab.delideli.enums.PaymentStatus;
import flab.delideli.enums.PaymentType;
import java.time.LocalDateTime;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class PaymentDTO {

	@NotNull
	private Long orderId;
	@NotBlank
	private String userId;
	@NotNull
	private PaymentType paymentType;
	@NotNull
	private Long amountPaid;
	@NotNull
	private PaymentStatus paymentStatus;
	@NotNull
	private LocalDateTime paymentDate;

	@Builder
	public PaymentDTO(Long orderId, String userId, PaymentType paymentType, Long amountPaid,
		PaymentStatus paymentStatus, LocalDateTime paymentDate) {
		this.orderId = orderId;
		this.userId = userId;
		this.paymentType = paymentType;
		this.amountPaid = amountPaid;
		this.paymentStatus = paymentStatus;
		this.paymentDate = paymentDate;
	}

}