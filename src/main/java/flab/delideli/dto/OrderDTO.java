package flab.delideli.dto;

import java.time.LocalDate;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import flab.delideli.enums.OrderStatus;
import flab.delideli.enums.PaymentType;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.core.annotation.Order;

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
	private PaymentType paymentType;

	private long totalPrice;

	private long shopId;
	@NotNull
	private OrderStatus orderStatus;

	private String request;

	private LocalDate orderDate;

	@Builder
	public OrderDTO(String userId, String address, String phoneNumber, PaymentType paymentType,
	                long totalPrice, long shopId, String request, LocalDate orderDate, OrderStatus orderStatus) {
		this.userId = userId;
		this.address = address;
		this.phoneNumber = phoneNumber;
		this.paymentType = paymentType;
		this.totalPrice = totalPrice;
		this.shopId = shopId;
		this.request = request;
		this.orderDate = orderDate;
		this.orderStatus = orderStatus;
	}

}
