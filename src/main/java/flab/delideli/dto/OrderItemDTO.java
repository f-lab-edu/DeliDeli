package flab.delideli.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class OrderItemDTO {

	@NotBlank
	private String menuName;
	@NotNull
	private Long menuPrice;
	@NotNull
	private Long menuQuantity;
	@NotNull
	private Long orderId;

	@Builder
	public OrderItemDTO(String menuName, Long menuPrice, Long menuQuantity, Long orderId) {
		this.menuName = menuName;
		this.menuPrice = menuPrice;
		this.menuQuantity = menuQuantity;
		this.orderId = orderId;
	}
}
