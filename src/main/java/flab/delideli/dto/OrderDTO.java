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

	private Long orderId;
	@NotBlank
	private String userId;
	@NotBlank
	private String address;
	@NotBlank
	private String phoneNumber;

	private int totalPrice;
	@NotBlank
	private String paymentMethod;

	private LocalDate orderDate;
	@NotNull
	private Long shopId;

	private String request;

	@Builder
	public OrderDTO(String userId, String address, String phoneNumber, int totalPrice,
		String paymentMethod, LocalDate orderDate, Long shopId, String request) {
		this.userId = userId;
		this.address = address;
		this.phoneNumber = phoneNumber;
		this.totalPrice = totalPrice;
		this.paymentMethod = paymentMethod;
		this.orderDate = orderDate;
		this.shopId = shopId;
		this.request = request;
	}

}