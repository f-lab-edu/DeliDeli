package flab.delideli.service;

import flab.delideli.dao.CartDao;
import flab.delideli.dto.AddCartDTO;
import flab.delideli.dto.CartlistDTO;
import lombok.AllArgsConstructor;
import org.apache.ibatis.jdbc.Null;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CartServiceImpl implements CartService {

    private final CartDao cartDao;

    @Override
    public void insertCart(AddCartDTO addCartDTO, String userId) {
        cartDao.insertCart(addCartDTO,userId);
    }

    @Override
    public boolean isItemInCart(AddCartDTO addCartDTO, String userId) {
        if (cartDao.isItemInCart(addCartDTO, userId) != null)
            return true;
        return false;
    }

    @Override
    public void updateCartItem(AddCartDTO addCartDTO, String userId) {
        cartDao.updateCartItem(addCartDTO, userId);
    }

}
