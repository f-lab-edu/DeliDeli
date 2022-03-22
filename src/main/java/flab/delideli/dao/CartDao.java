package flab.delideli.dao;

import flab.delideli.dto.AddCartDTO;
import flab.delideli.dto.CartlistDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface CartDao {

    void insertCart(@Param("addCartDTO") AddCartDTO addCartDTO, @Param("userid") String userId);

    Integer isItemInCart(@Param("addCartDTO") AddCartDTO addCartDTO, @Param("userId") String userId);

    void updateCartItem(@Param("addCartDTO") AddCartDTO addCartDTO, @Param("userId") String userId);

    List<CartlistDTO> getCartList(@Param("userId") String userId);

    void clearCart(@Param("userId") String userId);
}
