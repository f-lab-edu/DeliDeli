package flab.delideli.dao;

import flab.delideli.dto.MenuDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import flab.delideli.dto.UpdateMenuDTO;

@Mapper
public interface MenuDao {

	void insertMenu(MenuDTO menuDTO);

	boolean isExistMenu(@Param("menuName") String menuName, @Param("shopId") Long shopId);

	void updateMenu(@Param("updateMenuDTO") UpdateMenuDTO updateMenuDTO);


}
