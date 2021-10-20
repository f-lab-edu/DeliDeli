package flab.delideli.dto;

import flab.delideli.enums.MenuStock;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UpdateMenuDTO {

	@NotNull
	private Long menuId;
	@NotNull
	private Long menuPrice;
	@NotNull
	private MenuStock menuStock;

	private String menuInfo;

}
