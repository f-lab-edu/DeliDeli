package flab.delideli.service;

import flab.delideli.dao.CartDao;
import flab.delideli.dto.AddCartDTO;
import flab.delideli.dto.CartlistDTO;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class CartServiceImpl implements CartService {

    private final CartDao cartDao;

    @Override
    public void insertCart(AddCartDTO addCartDTO, String userId) {
        cartDao.insertCart(addCartDTO,userId);
    }

    @Override
    public List<CartlistDTO> getCartList(String userId) {
        List<CartlistDTO> cartlist= cartDao.getCartList(userId);
        return cartlist;
    }

    @Override
    public void clearCart(String userId) {
        cartDao.clearCart(userId);
    }
}
