package flab.delideli.service;

import flab.delideli.dao.CartDao;
import flab.delideli.dto.AddCartDTO;
import flab.delideli.dto.CartDTO;
import flab.delideli.dto.CartItemDTO;
import flab.delideli.dto.CartlistDTO;
import lombok.AllArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

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

	/*
		장바구니 물품 삭제
		요구사항 정리
		1. 삭제하려는 사람이 본인이 맞는지 확인해야합니다.
		2. 맞으면 원하는 물품을 삭제합니다.
		3. 본인이 아니면 잘못된접근이라는 예외를 보냅니다.
		4. cartItemId가 존재하지 않으면 예외를 보냅니다.
	 */
	@Override
	public void deleteCartItem(String userId, long cartItemId) {
		validateMatchCartOwner(cartItemId, userId);
		cartDao.deleteCartItem(cartItemId);
	}

	@Override
	public void clearCart(String userId) {
		cartDao.clearCart(userId);
	}

	private boolean isItemInCart(AddCartDTO addCartDTO, String userId) {
		if (cartDao.isItemInCart(addCartDTO, userId) != null) {
			return true;
		}
		return false;
	}

	private void validateMatchCartOwner(long cartItemId, String userId) {
		// 내 장바구니에 담긴게 아닌 것을 삭제하려고 하는 경우
		if (!userId.equals(cartDao.getCartOwnerId(cartItemId))) {
			throw new IllegalArgumentException("잘못된 값을 입력하셨습니다 : " + cartItemId);
		}
	}
}
