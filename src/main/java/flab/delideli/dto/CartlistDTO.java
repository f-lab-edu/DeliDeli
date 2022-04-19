package flab.delideli.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Getter
@NoArgsConstructor
public class CartlistDTO {

	@NotNull
	private long cartId;
	@NotNull
	private int menuId;
	@NotNull
	private String menuName;
	@NotNull
	private int price;
	@NotNull
	private int amount;
}
