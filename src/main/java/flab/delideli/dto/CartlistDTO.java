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
	private long menuId;
	@NotNull
	private String menuName;
	@NotNull
	private long price;
	@NotNull
	private long amount;
}
