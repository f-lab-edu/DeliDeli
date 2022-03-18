package flab.delideli.controller;

import flab.delideli.annotation.CurrentUser;
import flab.delideli.dto.AddCartDTO;
import flab.delideli.dto.CartlistDTO;
import flab.delideli.service.CartService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/users/{userid}/carts")
public class CartController {

    private CartService cartService;

    @PostMapping
    public void addCart(@RequestBody AddCartDTO addCartDTO, @CurrentUser String userId){
        if (cartService.isItemInCart(addCartDTO, userId)) {
            cartService.updateCartItem(addCartDTO, userId);
        }
        else {
            cartService.insertCart(addCartDTO, userId);
        }
    }
}
