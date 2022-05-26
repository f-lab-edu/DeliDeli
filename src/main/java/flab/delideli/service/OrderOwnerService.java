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

@Service
@RequiredArgsConstructor
public class OrderOwnerService implements OwnerService {

	private final OwnerDao ownerDao;
	private final ShopDao shopDao;
	private final PaymentDao paymentDao;

	/* 조리시작
        1. paymentStatus가 canceled상태가 아닐 경우에만 조리시작이 가능하다.
        2. 조리시작했을 때는 결제취소불가능한 상태로 변경해줌
    */
	@Override
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
	/* 조리 취소
        1. 사장이 취소하는 경우 조리취소로직으로 그대로 두고 paymentstatus를 canceled로직을 추가
	 */
	@Override
	public void updateOrderStatusCancel(long orderId, String userId) {
		validateCorrectOwnerShop(userId, orderId);
		OrderDTO orderDTO = getOrderDTO(orderId);
		if(orderDTO.getOrderStatus() != OrderStatus.ORDER_COMPLETE) {
			throw new IllegalStateException("주문취소할 수 없는 주문입니다.");
		}
		ownerDao.updateOrderStatusCancel(orderId);
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
