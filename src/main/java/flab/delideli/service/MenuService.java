package flab.delideli.service;

import flab.delideli.dao.MenuDao;
import flab.delideli.dto.MenuDTO;
import flab.delideli.dto.UpdateMenuDTO;
import flab.delideli.exception.AlreadyAddedValueException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MenuService {

	private final MenuDao menuDao;

	public void addMenu(MenuDTO menuDTO) {

		boolean isExistMenuName = menuDao.isExistMenuName(menuDTO.getMenuName(), menuDTO.getShopId());
		if(isExistMenuName) {
			throw new AlreadyAddedValueException("해당 가게에 이미 등록한 메뉴입니다.");
		}

		menuDao.insertMenu(menuDTO);

	}

	public void updateMenu(UpdateMenuDTO updateMenuDTO) {
		validateExistMenu(updateMenuDTO.getMenuId());
		validateMainMenuPriceNotZero(updateMenuDTO.getMenuId(), updateMenuDTO.getMenuPrice());
		menuDao.updateMenu(updateMenuDTO);
	}

	/*
	1. menuId만 매개변수를 받는다
	2. menuId로 해당 메뉴를 삭제한다
	3. 사장님이 해당가게 사장님이 맞는 지 확인한다
	4. menuId를 잘못된 값일경우 잘못된 입력입니다라는 예외를 보내준다
	 */
	public void deleteMenu(long menuId) {
		validateExistMenu(menuId);
		menuDao.deleteMenu(menuId);
	}

	private void validateExistMenu(long menuId) {
		boolean isExistMenuId = menuDao.isExistMenuId(menuId);
		if (!isExistMenuId) {
			throw new IllegalArgumentException("잘못된 입력입니다.");
		}
	}

	private void validateMainMenuPriceNotZero(long menuId, long menuPrice) {
		boolean isMainMenu = menuDao.selectIsMainMenu(menuId);
		if(isMainMenu && menuPrice == 0) {
			throw new IllegalStateException("메인메뉴는 0원이 될 수 없습니다.");
		}
	}

}