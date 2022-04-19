package flab.delideli.dao;

import flab.delideli.dto.AddCartDTO;
import flab.delideli.dto.CartItemDTO;
import flab.delideli.dto.CartlistDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface CartDao {

	void insertCart(@Param("addCartDTO") AddCartDTO addCartDTO, @Param("userid") String userId);

	List<CartlistDTO> getCartList(@Param("userId") String userId);

	void deleteCartItem(@Param("cartId") int cartItemId);

	List<CartItemDTO> getCartItemAmountAndPrice(@Param("userId") String userId);

	Integer isItemInCart(@Param("addCartDTO") AddCartDTO addCartDTO, @Param("userId") String userId);

	void updateCartItem(@Param("addCartDTO") AddCartDTO addCartDTO, @Param("userId") String userId);

	String getCartOwnerId(@Param("cartId") int cartItemId);

	void clearCart(@Param("userId") String userId);

	String getCartUserId(@Param("userId") String userId);
}
