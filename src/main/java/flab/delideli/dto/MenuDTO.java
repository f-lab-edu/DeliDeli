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
	private Long menuPrice;
	@NotNull
	private Long shopId;
	@NotNull
	private MenuStock menuStock;

	private String menuInfo;

	private FoodCategory menuCategory;

	@Builder
	public MenuDTO(String menuName, Long menuPrice, Long shopId,
		MenuStock menuStock, String menuInfo, FoodCategory menuCategory) {
		this.menuName = menuName;
		this.menuPrice = menuPrice;
		this.shopId = shopId;
		this.menuStock = menuStock;
		this.menuInfo = menuInfo;
		this.menuCategory = menuCategory;
	}

}