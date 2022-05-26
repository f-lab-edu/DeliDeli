package flab.delideli.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import flab.delideli.enums.PaymentType;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class RequestOrderDTO {

	@NotBlank
	private String address;
	@NotBlank
	private String phoneNumber;
	@NotNull
	private PaymentType paymentType;
	@NotNull
	private Long shopId;

	private String request;

	@Builder
	public RequestOrderDTO(String address, String phoneNumber,
		PaymentType paymentType, Long shopId, String request) {
		this.address = address;
		this.phoneNumber = phoneNumber;
		this.paymentType = paymentType;
		this.shopId = shopId;
		this.request = request;
	}

}