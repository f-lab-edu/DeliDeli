package flab.delideli.dao;

import flab.delideli.dto.MenuDTO;
import java.util.List;

public interface MenuDao {

	void insertMenu(MenuDTO menuDTO);

	void updateMenu(Long menuId);

	void deleteMenu(Long menuId);

	MenuDTO selectMenu(Long menuId);

	List<MenuDTO> selectMenusOfShop(Long shopId);

}
