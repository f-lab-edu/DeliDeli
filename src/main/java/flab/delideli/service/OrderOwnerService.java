package flab.delideli.service;

import flab.delideli.dao.OrderDao;
import flab.delideli.dao.OwnerDao;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderOwnerService implements OwnerService {

	private final OwnerDao ownerDao;

	@Override
	public void updateOrderStatusCooking(long orderId, String userId) {
		validateCorrectOwnerShop(userId);
		ownerDao.updateOrderStatusCooking(orderId);
	}

	private void validateCorrectOwnerShop(String userId) {

	}
}
