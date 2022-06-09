package flab.delideli.dto;

import flab.delideli.enums.FoodCategory;
import flab.delideli.enums.MenuStock;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
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
	private Boolean menuActivation;

	private String menuInfo;

	private FoodCategory menuCategory;

	@Builder
	public MenuDTO(String menuName, long menuPrice, long shopId,
		MenuStock menuStock, boolean mainMenu, Boolean menuActivation, String menuInfo, FoodCategory menuCategory) {
		this.menuName = menuName;
		this.menuPrice = menuPrice;
		this.shopId = shopId;
		this.menuStock = menuStock;
		this.mainMenu = mainMenu;
		this.menuActivation = menuActivation;
		this.menuInfo = menuInfo;
		this.menuCategory = menuCategory;
	}

}