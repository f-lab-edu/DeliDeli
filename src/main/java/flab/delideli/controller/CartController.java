package flab.delideli.controller;

import flab.delideli.annotation.CurrentUser;
import flab.delideli.dto.AddCartDTO;
import flab.delideli.dto.CartDTO;
import flab.delideli.dto.CartlistDTO;
import flab.delideli.service.CartService;
import flab.delideli.service.LoginService;
import flab.delideli.util.ResponseEntityCode;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/users/carts")
public class CartController {

	private CartService cartService;

	@PostMapping
	public void addCart(@RequestBody AddCartDTO addCartDTO, @CurrentUser String userId) {
		if (cartService.isItemInCart(addCartDTO, userId)) {
			cartService.updateCartItem(addCartDTO, userId);
		}
		cartService.insertCart(addCartDTO, userId);
	}

	@GetMapping
	public CartDTO getCartList(@CurrentUser String userId) {
		List<CartlistDTO> cartlist = cartService.getCartList(userId);
		int totalPrice = cartService.getCartTotalPrice(userId);
		CartDTO cartDTO = new CartDTO(cartlist, totalPrice);
		return cartDTO;
	}

	@DeleteMapping("/{cartItemId}")
	public ResponseEntity deleteCartItem(@CurrentUser String userId, @PathVariable @NotNull int cartItemId) {
		if (cartService.confirmUser(userId, cartItemId)) {
			cartService.deleteCartItem(userId, cartItemId);
			return ResponseEntityCode.OK_RESPONSE_ENTITY;
		} else {
			return ResponseEntityCode.FORBIDDEN_RESPONSE_ENTITY;
		}
	}

	@DeleteMapping()
	public void clearCart(@CurrentUser String userId) {
		cartService.clearCart(userId);
	}
}