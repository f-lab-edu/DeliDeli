package flab.delideli.dao;

import flab.delideli.dto.MenuDTO;
import java.util.List;

public interface MenuDao {

	void insertMenu(MenuDTO menuDTO);

	boolean isExistMenu(String menuName, Long shopId);

}
