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

	void deleteCart(@Param("userId") String userId, @Param("cartId") int cartId);

	void deleteCartItem(@Param("cartId") int cartItemId);

	List<CartItemDTO> getCartItemAmountAndPrice(@Param("userId") String userId);
}
