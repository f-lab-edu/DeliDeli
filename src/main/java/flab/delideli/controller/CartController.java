package flab.delideli.controller;

import flab.delideli.annotation.CurrentUser;
import flab.delideli.dto.AddCartDTO;
import flab.delideli.dto.CartDTO;
import flab.delideli.dto.CartlistDTO;
import flab.delideli.service.CartService;
import flab.delideli.service.LoginService;
import flab.delideli.util.ResponseEntityCode;
import lombok.AllArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/carts")
public class CartController {

	private CartService cartService;

	@PostMapping
	public void addCart(@RequestBody AddCartDTO addCartDTO, @CurrentUser String userId) {
		cartService.addItemInCart(addCartDTO, userId);
	}

	@GetMapping
	public CartDTO getCartList(@CurrentUser String userId) {
		CartDTO cartDTO = cartService.getCartList(userId);
		return cartDTO;
	}

	@DeleteMapping("/{cartItemId}")
	public void deleteCartItem(@CurrentUser String userId, @PathVariable long cartItemId) {
		cartService.deleteCartItem(userId, cartItemId);
	}

	@DeleteMapping()
	public void clearCart(@CurrentUser String userId) {
		cartService.clearCart(userId);
	}
}
