package flab.delideli.service;

import flab.delideli.dto.AddCartDTO;

public interface CartService {
    void insertCart(AddCartDTO addCartDTO, String userId);
}
