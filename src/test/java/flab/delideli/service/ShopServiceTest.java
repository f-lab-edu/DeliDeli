package flab.delideli.service;

import flab.delideli.dao.ShopDao;
import flab.delideli.dao.test.TestDao;
import flab.delideli.dto.ShopDTO;
import flab.delideli.enums.FoodCategory;
import flab.delideli.exception.AlreadyAddedValueException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ShopServiceTest {

	@Autowired
	private ShopService shopService;
	@Autowired
	private TestDao testDao;
	@Autowired
	private ShopDao shopDao;

	private ShopDTO shopDTO1;
	private ShopDTO shopDTO2;
	private ShopDTO shopDTO3;
	private ShopDTO shopDTO4;


	@BeforeEach
	void setUp() {
		shopDTO1 = new ShopDTO("맛난떡볶이", "02-111-1111", "서울시 가나구 다라동", "떡볶이 판매", "서울",
				FoodCategory.KOREAN_FOOD, "쉬는시간: 15~16시 / 휴뮤없음", "영업시간: 9~21시",
				"111-111-11111", "suykim");
		shopDao.insertShop(shopDTO1);
		shopDTO2 = new ShopDTO("맛난떡볶이", "02-111-1111", "서울시 가나구 다라동", "떡볶이 판매", "서울",
				FoodCategory.KOREAN_FOOD, "쉬는시간: 15~16시 / 휴뮤없음", "영업시간: 9~21시",
				"111-111-11111", "suykim");
		shopDTO3 = new ShopDTO("맛난떡볶이", "02-111-1111", "서울시 가나구 다라동", "떡볶이 판매", "서울",
				FoodCategory.KOREAN_FOOD, "쉬는시간: 15~16시 / 휴뮤없음", "영업시간: 9~21시",
				"111-111-11111", "ddaadd");
		shopDTO4 = new ShopDTO("맛난피자", "02-112-1111", "서울시 가나구 마마동", "피자 판매", "서울",
				FoodCategory.PIZZA, "쉬는시간: 15~16시 / 휴뮤없음", "영업시간: 9~21시",
				"111-111-11111", "suykim");
	}

	@AfterEach
	void tearDown() {
		testDao.deleteShopDTO(shopDTO1);
		testDao.deleteShopDTO(shopDTO4);
	}
	/*
	가게 등록
	1. 사장님권한일 때만 가게등록을 할 수 있다
	2. 매개변수로 shopDTO을 받아서 가게를 등록한다
	3. 이미 등록된 가게일경우(가게이름과 가게위치) 예외를 던진다 -> 한 동네에 같은 이름의 매장을 내지 못하도록
    */

	@Test
	@DisplayName("사장님 계정으로 이미 등록되어 있는 매장일 경우에 AlreadyAddedValueException을 던진다")
	void registeringAlreadyExistingShopCreatesException() {
		assertThrows(AlreadyAddedValueException.class, () -> shopService.addShop(shopDTO2));
	}

	@Test
	@DisplayName("다른 사장님이 같은 이름의 매장을 등록하려고 할 때 AlreadyAddedValueException을 던진다")
	void registeringAlreadyExistingShopByOtherOwnerCreatesException() {
		assertThrows(AlreadyAddedValueException.class, () -> shopService.addShop(shopDTO3));
	}

	@Test
	@DisplayName("같은 이름으로 등록하는 경우가 아닐 때 가게 등록을 성공한다")
	void addShop() {
		shopService.addShop(shopDTO4);
	}
}