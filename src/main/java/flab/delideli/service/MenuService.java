package flab.delideli.service;

import flab.delideli.dao.MenuDao;
import flab.delideli.dto.MenuDTO;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MenuService {

	private final MenuDao menuDao;

	public void addMenu(MenuDTO menuDTO) {
		menuDao.insertMenu(menuDTO);
	}

	public void updateMenu(Long menuId) {
		menuDao.updateMenu(menuId);
	}

	public void deleteMenu(Long menuId) {
		menuDao.deleteMenu(menuId);
	}

	public MenuDTO getMenu(Long menuId) {
		return menuDao.selectMenu(menuId);
	}

	public List<MenuDTO> getMenusOfShop(Long shopId) {
		return menuDao.selectMenusOfShop(shopId);
	}

}
