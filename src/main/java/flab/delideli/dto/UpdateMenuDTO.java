package flab.delideli.dto;

import flab.delideli.enums.FoodCategory;
import flab.delideli.enums.MenuStock;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UpdateMenuDTO {

	@NotNull
	private long menuId;

	private long menuPrice;

	private MenuStock menuStock;

	private String menuInfo;

	private FoodCategory menuCategory;

	private Boolean menuActivation;

}
