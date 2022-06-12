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

	@BeforeEach
	void setUp() {
		shopDTO1 = new ShopDTO("맛난떡볶이", "02-111-1111", "서울시 가나구 다라동", "떡볶이 판매", "서울",
				FoodCategory.KOREAN_FOOD, "쉬는시간: 15~16시 / 휴뮤없음", "영업시간: 9~21시",
				"111-111-11111", "suykim");
		shopDao.insertShop(shopDTO1);
		shopDTO2 = new ShopDTO("맛난떡볶이", "02-111-1111", "서울시 가나구 다라동", "떡볶이 판매", "서울",
				FoodCategory.KOREAN_FOOD, "쉬는시간: 15~16시 / 휴뮤없음", "영업시간: 9~21시",
				"111-111-11111", "suykim");
	}

	@AfterEach
	void tearDown() {

	}
	/*
	가게 등록
	1. 사장님권한일 때만 가게등록을 할 수 있다
	2. 매개변수로 shopDTO을 받아서 가게를 등록한다
	3. 이미 등록된 가게일경우(가게이름과 사장님 계정으로) 예외를 던진다 -> 같은 사장이 여러개의 같은이름을 못내게
    */

	@Test
	@DisplayName("사장님 계정으로 이미 등록되어 있는 매장일 경우에 AlreadyAddedValueException을 던진다")
	void registeringAlreadyExistingShopCreatesException() {
		assertThrows(AlreadyAddedValueException.class, () -> shopService.addShop(shopDTO2));
	}
}