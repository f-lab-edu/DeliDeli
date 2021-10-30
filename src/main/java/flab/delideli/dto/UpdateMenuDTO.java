package flab.delideli.dto;

import flab.delideli.enums.FoodCategory;
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

	private Long menuPrice;

	private MenuStock menuStock;

	private String menuInfo;

	private FoodCategory menuCategory;

	private Boolean menuActivation;

}
