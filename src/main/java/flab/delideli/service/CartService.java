package flab.delideli.service;

import flab.delideli.dto.AddCartDTO;
import java.util.List;

public interface CartService {

    void insertCart(AddCartDTO addCartDTO, String userId);

    boolean isItemInCart(AddCartDTO addCartDTO, String userId);

    void updateCartItem(AddCartDTO addCartDTO, String userId);

    List getCartList(String userId);

    void clearCart(String userId);
}
