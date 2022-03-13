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
    public int checkCartItem(AddCartDTO addCartDTO, String userId) {
        if (cartDao.checkCartItem(addCartDTO, userId) != null)
            return 1;
        return 0;
    }

    @Override
    public void updateCartItem(AddCartDTO addCartDTO, String userId) {
        cartDao.updateCartItem(addCartDTO, userId);
    }

}
