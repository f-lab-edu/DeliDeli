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

	@Test
	@DisplayName("이미 등록되어 있는 매장의 이름으로 등록하려고 할 경우에 AlreadyAddedValueException을 던진다")
	void registeringAlreadyExistingShopCreatesException() {
		assertThrows(AlreadyAddedValueException.class, () -> shopService.addShop(shopDTO2));
	}

	@Test
	@DisplayName("같은 이름으로 등록하는 경우가 아닐 때 가게 등록을 성공한다")
	void addShop() {
		shopService.addShop(shopDTO4);
	}

	/* 하나의 가게 조회
	1. 사장님 권한일 때만 가게를 조회할 수 있다.
	2. 해당가게의 주인이 맞는지 확인해준다 ->shopid로 불러오는 ownerid랑 들어오는 ownerid가 같은지 확인
	3. 다르면 IllegalArgumentException을 던진다
	4. shopid와 ownerid를 해당가게를 조회할 수 있게한다
	 */
	@Test
	@DisplayName("해당 가게의 주인이 아닌 사람이 조회하려고 할 때 IllegalArgumentException을 던진다")
	void nonOwnerAccessCreatesException() {
		assertThrows(IllegalArgumentException.class, () -> shopService.getShopByShopIdAndOwnerId(testDao.selectShopId(shopDTO1.getShopName(),shopDTO1.getOwnerId()), "ddaad"));
	}

	@Test
	@DisplayName("해당 가게의 주인이 맞다면 가게 조회가 성공한다.")
	void getShop() {
		shopService.getShopByShopIdAndOwnerId(testDao.selectShopId(shopDTO1.getShopName(), shopDTO1.getOwnerId()),"suykim");
	}
}