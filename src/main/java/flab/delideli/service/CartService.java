package flab.delideli.service;

import flab.delideli.dto.AddCartDTO;
import flab.delideli.dto.CartlistDTO;

public interface CartService {

    void insertCart(AddCartDTO addCartDTO, String userId);

    boolean isItemInCart(AddCartDTO addCartDTO, String userId);

    void updateCartItem(AddCartDTO addCartDTO, String userId);

}
