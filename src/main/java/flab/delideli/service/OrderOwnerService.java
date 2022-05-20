package flab.delideli.service;

import flab.delideli.dao.OwnerDao;
import flab.delideli.dao.ShopDao;
import flab.delideli.dto.OrderDTO;
import flab.delideli.enums.OrderStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderOwnerService implements OwnerService {

	private final OwnerDao ownerDao;
	private final ShopDao shopDao;

	@Override
	public void updateOrderStatusCooking(long orderId, String userId) {
		validateCorrectOwnerShop(userId, orderId);
		OrderDTO orderDTO = getOrderDTO(orderId);
		if (orderDTO.getOrderStatus() == OrderStatus.ORDER_COMPLETE) {
			ownerDao.updateOrderStatusCooking(orderId);
		}
		throw new IllegalStateException("조리시작될 수 없는 주문입니다.");
	}

	@Override
	public void updateOrderStatusCancel(long orderId, String userId) {
		validateCorrectOwnerShop(userId, orderId);
		OrderDTO orderDTO = getOrderDTO(orderId);
		if(orderDTO.getOrderStatus() == OrderStatus.ORDER_COMPLETE) {
			ownerDao.updateOrderStatusCancel(orderId);
		}
		throw new IllegalStateException("주문취소할 수 없는 주문입니다.");
	}

	@Override
	public void updateOrderStatusCookingComplete(long orderId, String userId) {
		validateCorrectOwnerShop(userId, orderId);
		OrderDTO orderDTO = getOrderDTO(orderId);
		if (orderDTO.getOrderStatus() == OrderStatus.START_COOKING) {
			ownerDao.updateOrderStatusCookingComplete(orderId);
		}
		throw new IllegalStateException("조리완료가 될 수 없는 주문입니다");
	}

	/*주문이 들어온 곳이 사장님 가게의 주문이 맞는지 확인해야한다*/
	private void validateCorrectOwnerShop(String userId, long orderId) {
		long shopId = ownerDao.getShopIdInOrders(orderId);
		if (!userId.equals(shopDao.getOwnerIdInShops(shopId))) {
			throw new IllegalArgumentException("잘못된 값이 들어왔습니다.");
		}
	}

	private OrderDTO getOrderDTO(long orderId) {
		return ownerDao.selectOrderDTO(orderId);
	}

}
