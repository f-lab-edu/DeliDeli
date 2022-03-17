package flab.delideli.dto;

import java.time.LocalDate;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class OrderDTO {

	@NotBlank
	private String userId;
	@NotBlank
	private String address;
	@NotBlank
	private String phoneNumber;
	@NotBlank
	private String paymentMethod;
	@NotNull
	private int totalPrice;
	@NotNull
	private Long shopId;

	private String request;

	private LocalDate orderDate;

	@Builder
	public OrderDTO(String userId, String address, String phoneNumber, String paymentMethod,
		int totalPrice, Long shopId, String request, LocalDate orderDate) {
		this.userId = userId;
		this.address = address;
		this.phoneNumber = phoneNumber;
		this.paymentMethod = paymentMethod;
		this.totalPrice = totalPrice;
		this.shopId = shopId;
		this.request = request;
		this.orderDate = orderDate;
	}

}
