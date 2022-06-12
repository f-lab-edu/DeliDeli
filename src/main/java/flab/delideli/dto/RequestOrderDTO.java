package flab.delideli.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import flab.delideli.enums.PaymentType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RequestOrderDTO {

	@NotBlank
	private String address;
	@NotBlank
	private String phoneNumber;
	@NotNull
	private PaymentType paymentType;
	@NotNull
	private long shopId;

	private String request;

}