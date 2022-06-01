package flab.delideli.service;

import flab.delideli.dao.MenuDao;
import flab.delideli.dto.MenuDTO;
import flab.delideli.enums.FoodCategory;
import flab.delideli.enums.MenuStock;
import flab.delideli.exception.AlreadyAddedValueException;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class MenuServiceTest {

	@Autowired
	private MenuService menuService;
	@Autowired
	private MenuDao menuDao;

	private MenuDTO menuDTO1;
	private MenuDTO menuDTO2;
	private MenuDTO menuDTO3;

	@BeforeEach
	void setUp() {
		menuDTO1 = new MenuDTO("라면", 15000, 1, MenuStock.DEFAULT, "신라면", FoodCategory.KOREAN_FOOD);
		menuDao.insertMenu(menuDTO1);
		menuDTO2 = new MenuDTO("라면", 15000, 1, MenuStock.DEFAULT, "신라면", FoodCategory.KOREAN_FOOD);
		menuDTO3 = new MenuDTO("스파게티", 16000, 1, MenuStock.DEFAULT, "토마토", FoodCategory.WESTERN_FOOD);
	}

	@AfterEach
	void tearDown() {
		menuDao.deleteMenuDTO(menuDTO1);
		menuDao.deleteMenuDTO(menuDTO3);
	}

	@Test //이미 존재하는 메뉴일때 예외를 출력하는지 확인하는 테스트. 이미 존재하는 메뉴를 넣으면 예외를 불러온다.
	void PuttingAlreadyExistingMenuCreatesException() {
		assertThrows(AlreadyAddedValueException.class, () -> menuService.addMenu(menuDTO2));
	}

	@Test // 존재하지않으면 등록하는 테스트
	void AddIfMenuDoesNotExist() {
		menuService.addMenu(menuDTO3);
	}

}