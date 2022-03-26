package flab.delideli.service;

import flab.delideli.dto.AddCartDTO;

import java.util.List;

public interface CartService {
    void insertCart(AddCartDTO addCartDTO, String userId);

    List getCartList(String userId);

    int getCartTotalPrice(String userId);

    void deleteCart(String userId, int cartId);

    void deleteCartItem(String userId, int cartItemId);
}
