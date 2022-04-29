package flab.delideli.service;

import flab.delideli.dto.AddCartDTO;
import flab.delideli.dto.CartlistDTO;

import java.util.List;

public interface CartService {

	void addItemInCart(AddCartDTO addCartDTO, String userid);

	boolean isItemInCart(AddCartDTO addCartDTO, String userId);

	long getCartTotalPrice(String userId);

	List getCartList(String userId);

	void deleteCartItem(String userId, int cartItemId);

	boolean confirmUser(String userId, int cartItemId);

	void clearCart(String userId);
}
