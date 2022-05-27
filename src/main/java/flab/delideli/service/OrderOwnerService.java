package flab.delideli.service;

import flab.delideli.dao.OwnerDao;
import flab.delideli.dao.PaymentDao;
import flab.delideli.dao.ShopDao;
import flab.delideli.dto.OrderDTO;
import flab.delideli.dto.PaymentDTO;
import flab.delideli.enums.OrderStatus;
import flab.delideli.enums.PaymentStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class OrderOwnerService implements OwnerService {

	private final OwnerDao ownerDao;
	private final ShopDao shopDao;
	private final PaymentDao paymentDao;

	@Override
	@Transactional
	public void updateOrderStatusCooking(long orderId, String userId) {
		validateCorrectOwnerShop(userId, orderId);
		OrderDTO orderDTO = getOrderDTO(orderId);
		PaymentDTO paymentDTO = paymentDao.selectPaymentDTO(orderId);
		if (paymentDTO.getPaymentStatus() == PaymentStatus.CANCELED || orderDTO.getOrderStatus() != OrderStatus.ORDER_COMPLETE) {
			throw new IllegalStateException("조리시작될 수 없는 주문입니다.");
		}
		ownerDao.updateOrderStatusCooking(orderId);
		paymentDao.updatePaymentStatusUnableCancel(orderId);
	}

	@Override
	@Transactional
	public void updateOrderStatusCancel(long orderId, String userId) {
		validateCorrectOwnerShop(userId, orderId);
		OrderDTO orderDTO = getOrderDTO(orderId);
		PaymentDTO paymentDTO = paymentDao.selectPaymentDTO(orderId);
		if (orderDTO.getOrderStatus() != OrderStatus.ORDER_COMPLETE) {
			throw new IllegalStateException("주문취소할 수 없는 주문입니다.");
		} else if (paymentDTO.getPaymentStatus() == PaymentStatus.CANCELED) {
			throw new IllegalStateException("고객요청에 의해 이미 주문취소된 주문입니다.");
		}
		ownerDao.updateOrderStatusCancel(orderId);
		paymentDao.updatePaymentStatusCanceledByOwner(orderId);
	}

	@Override
	public void updateOrderStatusCookingComplete(long orderId, String userId) {
		validateCorrectOwnerShop(userId, orderId);
		OrderDTO orderDTO = getOrderDTO(orderId);
		if (orderDTO.getOrderStatus() != OrderStatus.START_COOKING) {
			throw new IllegalStateException("조리완료가 될 수 없는 주문입니다");
		}
		ownerDao.updateOrderStatusCookingComplete(orderId);
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
