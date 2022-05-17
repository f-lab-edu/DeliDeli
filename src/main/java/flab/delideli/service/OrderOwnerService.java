package flab.delideli.service;

import flab.delideli.dao.OwnerDao;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderOwnerService implements OwnerService {

	private final OwnerDao ownerDao;

	@Override
	public void updateOrderStatusCooking(long orderId, String userId) {
		validateCorrectOwnerShop(userId, orderId);
		ownerDao.updateOrderStatusCooking(orderId);
	}

	@Override
	public void updateOrderStatusCancel(long orderId, String userId) {
		validateCorrectOwnerShop(userId, orderId);
		ownerDao.updateOrderStatusCancel(orderId);
	}

	private void validateCorrectOwnerShop(String userId, long orderId) {
		if (ownerDao.getShopIdInOrders(orderId) != ownerDao.getShopIdInShops(userId)) {
			throw new IllegalStateException("잘못된 값이 들어왔습니다." + ownerDao.getShopIdInShops(userId));
		}
	}
}
