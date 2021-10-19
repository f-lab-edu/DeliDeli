package flab.delideli.dao;

import flab.delideli.dto.MenuDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface MenuDao {

	void insertMenu(MenuDTO menuDTO);

	boolean isExistMenu(@Param("menuName") String menuName, @Param("shopId") Long shopId);

}
