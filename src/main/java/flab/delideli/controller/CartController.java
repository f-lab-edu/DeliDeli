package flab.delideli.controller;

import flab.delideli.annotation.CurrentUser;
import flab.delideli.dto.AddCartDTO;
import flab.delideli.service.CartService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/users/{userid}/carts")
public class CartController {

    private CartService cartService;

    @PostMapping
    public void addCart(@RequestBody AddCartDTO addCartDTO, @CurrentUser String userId){
        cartService.insertCart(addCartDTO,userId);
    }

}
