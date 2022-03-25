package flab.delideli.dao;

import flab.delideli.dto.MenuDTO;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import flab.delideli.dto.UpdateMenuDTO;

@Mapper
public interface MenuDao {

	void insertMenu(MenuDTO menuDTO);

	boolean isExistMenuName(@Param("menuName") String menuName, @Param("shopId") Long shopId);

	int getMenuCount(List<Long> cartMenuIds);

	List<Long> getMenuPriceList(List<Long> cartMenuIds);

	void updateMenu(@Param("updateMenuDTO") UpdateMenuDTO updateMenuDTO);

	void deleteMenu(@Param("menuId") Long menuId, @Param("shopId") Long shopId);

}
