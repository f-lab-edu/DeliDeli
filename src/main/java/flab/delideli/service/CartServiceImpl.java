package flab.delideli.service;

import flab.delideli.dao.CartDao;
import flab.delideli.dto.AddCartDTO;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CartServiceImpl implements CartService{

    private final CartDao cartDao;

    @Override
    public void insertCart(AddCartDTO addCartDTO, String userId) {
        cartDao.insertCart(addCartDTO,userId);
    }
}
