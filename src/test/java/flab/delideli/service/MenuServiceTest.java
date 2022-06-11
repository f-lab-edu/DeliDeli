package flab.delideli.service;

import flab.delideli.dao.MenuDao;
import flab.delideli.dao.test.TestDao;
import flab.delideli.dto.MenuDTO;
import flab.delideli.dto.UpdateMenuDTO;
import flab.delideli.enums.FoodCategory;
import flab.delideli.enums.MenuStock;
import flab.delideli.exception.AlreadyAddedValueException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class MenuServiceTest {

	@Autowired
	private MenuService menuService;
	@Autowired
	private TestDao testDao;
	@Autowired
	private MenuDao menuDao;

	private MenuDTO menuDTO1;
	private MenuDTO menuDTO2;
	private MenuDTO menuDTO3;

	@BeforeEach
	void setUp() {
		menuDTO1 = new MenuDTO("라면", 15000, 1, MenuStock.DEFAULT, Boolean.TRUE, Boolean.TRUE, "신라면", FoodCategory.KOREAN_FOOD);
		menuDao.insertMenu(menuDTO1);
		menuDTO2 = new MenuDTO("라면", 15000, 1, MenuStock.DEFAULT, Boolean.TRUE, Boolean.TRUE,"신라면", FoodCategory.KOREAN_FOOD);
		menuDTO3 = new MenuDTO("스파게티", 16000, 1, MenuStock.DEFAULT, Boolean.TRUE, Boolean.TRUE, "토마토", FoodCategory.WESTERN_FOOD);
	}

	@AfterEach
	void tearDown() {
		menuDTO1 = testDao.selectMenuDTO("라면", 1);
		testDao.deleteMenuDTO(menuDTO1);
		testDao.deleteMenuDTO(menuDTO3);
	}

	@Test
	@DisplayName("이미 존재하는 메뉴일 때 AlreadyAddedValueException를 던진다")
	void puttingAlreadyExistingMenuCreatesException() {
		assertThrows(AlreadyAddedValueException.class, () -> menuService.addMenu(menuDTO2));
	}

	@Test
	@DisplayName("기존에 없던 메뉴라면 메뉴 등록을 성공한다")
	void addIfMenuDoesNotExist() {
		menuService.addMenu(menuDTO3);
	}

	@Test
	@DisplayName("기존에 없던 메뉴를 수정하려고 한다면 illegalArgumentException를 던진다")
	void editingNotExistingMenuCreatesException() {
		assertThrows(IllegalArgumentException.class, () -> menuService.updateMenu(new UpdateMenuDTO
				(1000, 0, MenuStock.DEFAULT, "열라면", FoodCategory.KOREAN_FOOD, Boolean.TRUE)));
	}

	@Test
	@DisplayName("메인메뉴의 가격을 0원으로 변경하려고 할 때 illegalStateException를 던진다")
	void changingMenuPriceToZeroCreatesException() {
		assertThrows(IllegalStateException.class, () -> menuService.updateMenu(UpdateMenuDTO.builder()
				.menuId(testDao.selectMenuId("라면", 1)).menuPrice(0).build()
		));
	}

	@Test
	@DisplayName("기존에 있는 메뉴이고, 가격을 0으로 바꾸는 경우가 아닐 때 메뉴 수정을 성공한다")
	void updateMenu() {
		menuService.updateMenu(UpdateMenuDTO.builder()
				.menuId(testDao.selectMenuId(menuDTO1.getMenuName(), menuDTO1.getShopId())).menuPrice(12400).build());
	}

	@Test
	@DisplayName("기존에 없던 메뉴를 삭제하려고 한다면 illegalArgumentException를 던진다")
	void deletingNotExistingMenuCreatesException() {
		assertThrows(IllegalArgumentException.class, () -> menuService.deleteMenu(100000));
	}
	
	@Test
	@DisplayName("기존에 있는 메뉴 삭제를 성공한다")
	void deleteMenu() {
		menuService.deleteMenu(testDao.selectMenuId("라면", 1));
	}
}