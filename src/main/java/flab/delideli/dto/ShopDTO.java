package flab.delideli.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ShopDTO {

	public enum FoodCategory {
		KOREAN_FOOD, JAPANESE_FOOD, CHINESE_FOOD,
		ASIAN_FOOD, WESTERN_FOOD,
		BURGER, PIZZA, CHICKEN, SNACK_BAR, CAFE_DESSERT
	}

	@NotBlank
	private String shopName;
	@NotBlank
	private String shopTel;
	@NotBlank
	private String shopLocation;
	@NotBlank
	private String shopInfo;
	@NotBlank
	private String deliveryArea;
	@NotNull
	private FoodCategory foodCategory;
	@NotBlank
	private String notice;
	@NotBlank
	private String operatingTime;
	@NotBlank
	private String businessNum;
	@NotBlank
	private String ownerId;

	@Builder
	public ShopDTO(String shopName, String shopTel, String shopLocation, String shopInfo,
		String deliveryArea, FoodCategory foodCategory, String notice, String operatingTime,
		String businessNum, String ownerId) {
		this.shopName = shopName;
		this.shopTel = shopTel;
		this.shopLocation = shopLocation;
		this.shopInfo = shopInfo;
		this.deliveryArea = deliveryArea;
		this.foodCategory = foodCategory;
		this.notice = notice;
		this.operatingTime = operatingTime;
		this.businessNum = businessNum;
		this.ownerId = ownerId;
	}

}
