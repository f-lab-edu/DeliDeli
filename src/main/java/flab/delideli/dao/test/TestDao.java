package flab.delideli.dao.test;

import flab.delideli.dto.MenuDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface TestDao {

	void deleteMenuDTO(MenuDTO menuDTO);

	long selectMenuId(@Param("menuName") String menuName, @Param("shopId") long shopId);

	MenuDTO selectMenuDTO(@Param("menuName") String menuName, @Param("shopId") long shopId);
}
