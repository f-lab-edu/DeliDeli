package flab.delideli.service;

import flab.delideli.dao.CartDao;
import flab.delideli.dto.AddCartDTO;
import flab.delideli.dto.CartDTO;
import flab.delideli.dto.CartItemDTO;
import flab.delideli.dto.CartlistDTO;
import lombok.AllArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class CartServiceImpl implements CartService {

	private final CartDao cartDao;

	@Override
	public void addItemInCart(AddCartDTO addCartDTO, @NotNull String userid) {
		if (isItemInCart(addCartDTO, userid)) {
			cartDao.updateCartItem(addCartDTO, userid);
		}
		else
			cartDao.insertCart(addCartDTO, userid);
	}

	@Override
	public CartDTO getCartList(String userId) {
		List<CartItemDTO> cartItemDTOS = cartDao.getCartItemAmountAndPrice(userId);
		long totalPrice = cartItemDTOS.stream().mapToLong(x -> x.getItemPrice() * x.getItemAmount()).sum();
		List<CartlistDTO> cartlist = cartDao.getCartList(userId);
		CartDTO cartDTO = new CartDTO(cartlist, totalPrice);
		return cartDTO;
	}

	@Override
	public void deleteCartItem(String userId, int cartItemId) {
		cartDao.deleteCartItem(cartItemId);
	}

	@Override
	public boolean confirmUser(String userId, int cartItemId) {
		return cartDao.getCartOwnerId(cartItemId).equals(userId);
	}

	@Override
	public boolean isItemInCart(AddCartDTO addCartDTO, String userId) {
		if (cartDao.isItemInCart(addCartDTO, userId) != null) {
			return true;
		}
		return false;
	}

	@Override
	public void clearCart(String userId) {
		cartDao.clearCart(userId);
	}
}
