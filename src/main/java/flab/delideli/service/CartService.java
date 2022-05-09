package flab.delideli.service;

import flab.delideli.dto.AddCartDTO;
import flab.delideli.dto.CartDTO;
import flab.delideli.dto.CartlistDTO;

import java.util.List;

public interface CartService {

	void addItemInCart(AddCartDTO addCartDTO, String userid);

	CartDTO getCartList(String userId);

	void deleteCartItem(String userId, long cartItemId);

	void clearCart(String userId);
}
