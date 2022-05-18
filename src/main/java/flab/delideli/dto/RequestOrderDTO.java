package flab.delideli.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
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
	@NotBlank
	private String payType;
	@NotNull
	private Long shopId;

	private String request;

	@Builder
	public RequestOrderDTO(String address, String phoneNumber,
		String payType, Long shopId, String request) {
		this.address = address;
		this.phoneNumber = phoneNumber;
		this.payType = payType;
		this.shopId = shopId;
		this.request = request;
	}

}