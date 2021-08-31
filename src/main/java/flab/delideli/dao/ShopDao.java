package flab.delideli.dao;

import flab.delideli.dto.ShopDTO;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface ShopDao {

	void insertShop(ShopDTO shopDTO);

	boolean isExistShop(@Param("shopName") String shopName,
		@Param("ownerId") String ownerId);

	ShopDTO selectMyShop(@Param("shopName") String shopName,
		@Param("ownerId") String ownerId);

	List<ShopDTO> selectMyShopList(String ownerId);

}
