package flab.delideli.service;

import flab.delideli.dao.OrderDao;
import flab.delideli.dao.PaymentDao;
import flab.delideli.dao.ShopDao;
import flab.delideli.dto.OrderDTO;
import flab.delideli.enums.OrderStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class OrderOwnerService implements OwnerService {

	private final OrderDao orderDao;
	private final ShopDao shopDao;
	private final PaymentDao paymentDao;

	@Override
	@Transactional
	public void updateOrderStatusCooking(long orderId, String userId) {
		validateCorrectOwnerShop(userId, orderId);
		OrderDTO orderDTO = getOrderDTO(orderId);
		validateCorrectOrderStatus(orderDTO);
		orderDao.updateOrderStatusCooking(orderId);
		paymentDao.updatePaymentStatusUnableCancel(orderId);
	}

	@Override
	@Transactional
	public void updateOrderStatusCancel(long orderId, String userId) {
		validateCorrectOwnerShop(userId, orderId);
		OrderDTO orderDTO = getOrderDTO(orderId);
		validateCorrectOrderStatus(orderDTO);
		orderDao.updateOrderStatusCancel(orderId);
		paymentDao.updatePaymentStatusCanceledByOwner(orderId);
	}

	@Override
	public void updateOrderStatusCookingComplete(long orderId, String userId) {
		validateCorrectOwnerShop(userId, orderId);
		OrderDTO orderDTO = getOrderDTO(orderId);
		if (orderDTO.getOrderStatus() != OrderStatus.START_COOKING) {
			throw new IllegalStateException("조리완료가 될 수 없는 주문입니다");
		}
		orderDao.updateOrderStatusCookingComplete(orderId);
	}

	/*주문이 들어온 곳이 사장님 가게의 주문이 맞는지 확인해야한다*/
	private void validateCorrectOwnerShop(String userId, long orderId) {
		long shopId = orderDao.getShopIdInOrders(orderId);
		if (!userId.equals(shopDao.getOwnerIdInShops(shopId))) {
			throw new IllegalArgumentException("잘못된 값이 들어왔습니다.");
		}
	}

	private void validateCorrectOrderStatus(OrderDTO orderDTO) {
		if (orderDTO.getOrderStatus() == OrderStatus.ORDER_CANCEL) {
			throw new IllegalStateException("고객 요청에 의해 이미 취소된 주문입니다.");
		} else if (orderDTO.getOrderStatus() != OrderStatus.ORDER_COMPLETE) {
			throw new IllegalStateException("변경 불가능한 주문입니다.");
		}
	}

	private OrderDTO getOrderDTO(long orderId) {
		return orderDao.selectOrderDTO(orderId);
	}

}
