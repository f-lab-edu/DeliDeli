package flab.delideli.controller;

import flab.delideli.annotation.CurrentUser;
import flab.delideli.dto.AddCartDTO;
import flab.delideli.dto.CartlistDTO;
import flab.delideli.service.CartService;
import flab.delideli.service.LoginService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/users/{userid}/carts")
public class CartController {

    private CartService cartService;
    private LoginService sessionLoginService;

    @PostMapping
    public void addCart(@RequestBody AddCartDTO addCartDTO, @CurrentUser String userId){
        cartService.insertCart(addCartDTO,userId);
    }

    @GetMapping
    public List<CartlistDTO> getCartList(@CurrentUser String userId){
        List<CartlistDTO> cartlist=cartService.getCartList(userId);
        return cartlist;
    }

    @DeleteMapping("/{cartId}")
    public void deleteCart(@PathVariable int cartId, @CurrentUser String userId) {
        cartService.deleteCart(userId, cartId);
    }

    @DeleteMapping("/{cartItemId}")
    public void deleteCartItem(@CurrentUser String userId, @PathVariable int cartItemId) {
        cartService.deleteCartItem(userId, cartItemId);
    }
}