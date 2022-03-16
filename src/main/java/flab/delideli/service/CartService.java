package flab.delideli.service;

import flab.delideli.dto.AddCartDTO;
import flab.delideli.dto.CartlistDTO;

import java.util.List;

public interface CartService {
    void insertCart(AddCartDTO addCartDTO, String userId);

    List getCartList(String userId);

    void deleteCart(String userId, int cartId);
}
