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

		boolean isExistMenu = menuDao.isExistMenu(menuDTO.getMenuName(), menuDTO.getShopId());
		if(isExistMenu) {
			throw new AlreadyAddedValueException("해당 가게에 이미 등록한 메뉴입니다.");
		}

		menuDao.insertMenu(menuDTO);

	}

	public void updateMenu(UpdateMenuDTO updateMenuDTO) {
		menuDao.updateMenu(updateMenuDTO);
	}

}