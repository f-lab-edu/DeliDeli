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
		cartDao.insertCart(addCartDTO, userId);
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

    public List<CartlistDTO> getCartList(String userId) {
        List<CartlistDTO> cartlist= cartDao.getCartList(userId);
        return cartlist;
    }

	@Override
	public void clearCart(String userId) {
		cartDao.clearCart(userId);
	}
}
