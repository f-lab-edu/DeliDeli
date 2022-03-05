package flab.delideli.controller;

import flab.delideli.annotation.CurrentUser;
import flab.delideli.dto.AddCartDTO;
import flab.delideli.dto.CartlistDTO;
import flab.delideli.service.CartService;
import flab.delideli.service.LoginService;
import flab.delideli.service.SessionLoginService;
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
    public List<CartlistDTO> getCartList(){
        String currentUserId = sessionLoginService.getSessionUserId();
        List<CartlistDTO> cartlist=cartService.getCartList(currentUserId);
        return cartlist;
    }

    @DeleteMapping("/{cartId}")
    public void deleteCartItem(@PathVariable int cartId) {
        String currentUserId = sessionLoginService.getSessionUserId();
        cartService.deleteCartItem(currentUserId, cartId);
    }
}