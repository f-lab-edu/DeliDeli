package flab.delideli.dao;

import flab.delideli.dto.MenuDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import flab.delideli.dto.UpdateMenuDTO;

@Mapper
public interface MenuDao {

	void insertMenu(MenuDTO menuDTO);

	boolean isExistMenuName(@Param("menuName") String menuName, @Param("shopId") Long shopId);

	boolean isExistMenuId(Long menuId);

	int getMenuPrice(Long menuId);

	void updateMenu(@Param("updateMenuDTO") UpdateMenuDTO updateMenuDTO);

	void deleteMenu(@Param("menuId") Long menuId, @Param("shopId") Long shopId);

}
