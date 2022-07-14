package flab.delideli.dao;

import flab.delideli.dto.ShopDTO;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface ShopDao {

	void insertShop(ShopDTO shopDTO);

	boolean isExistShop(@Param("shopName") String shopName,
		@Param("shopLocation") String shopLocation);

	ShopDTO selectShopByShopIdAndOwnerId(@Param("shopId") long shopId,
		@Param("ownerId") String ownerId);

	List<ShopDTO> selectShopListByOwnerId(String ownerId);

	boolean isCurrentUserMatchingOwnerId(@Param("shopId") Long shopId,
		@Param("ownerId") String ownerId);

	String getOwnerIdInShops(long shopId);
}
