package flab.delideli.dto;

import flab.delideli.enums.FoodCategory;
import flab.delideli.enums.MenuStock;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MenuDTO {

	@NotBlank
	private String menuName;
	@NotNull
	private long menuPrice;
	@NotNull
	private long shopId;
	@NotNull
	private MenuStock menuStock;
	@NotNull
	private boolean mainMenu;
	@NotNull
	private boolean menuActivation;

	private String menuInfo;

	private FoodCategory menuCategory;

}