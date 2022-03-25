package flab.delideli.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@NoArgsConstructor
public class AddCartDTO {

	@NotNull
	private int menuId;
	@NotNull
	private int amount;
}
