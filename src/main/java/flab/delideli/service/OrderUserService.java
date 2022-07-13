package flab.delideli.service;

import flab.delideli.dao.CartDao;
import flab.delideli.dao.MenuDao;
import flab.delideli.dao.OrderDao;
import flab.delideli.dto.CartlistDTO;
import flab.delideli.dto.OrderDTO;
import flab.delideli.dto.OrderItemDTO;
import flab.delideli.dto.RequestOrderDTO;
import flab.delideli.enums.OrderStatus;
import flab.delideli.exception.MenuIdEmptyException;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.constraints.NotNull;

@Service
@RequiredArgsConstructor
public class OrderUserService implements OrderService {

	private final OrderDao orderDao;
	private final CartDao cartDao;
	private final MenuDao menuDao;

	@Override
	@Transactional
	public void registerOrder(RequestOrderDTO requestOrderDTO, String userId) {

		OrderDTO orderDTO = OrderDTO.builder()
				.userId(userId)
				.address(requestOrderDTO.getAddress())
				.phoneNumber(requestOrderDTO.getPhoneNumber())
				.paymentType(requestOrderDTO.getPaymentType())
				.totalPrice(getTotalPrice(userId))
				.shopId(requestOrderDTO.getShopId())
				.request(requestOrderDTO.getRequest())
				.orderDate(LocalDate.now())
				.orderStatus(OrderStatus.ORDER_COMPLETE).build();

		long orderId = orderDao.insertOrder(orderDTO);

		List<OrderItemDTO> orderItemDTOS =
			getOrderItemDTO(userId, orderId);

		registerOrderMenus(orderItemDTOS);

	}

	@Override
	@Transactional
	public void cancelOrder(long orderId, String userId) {
		OrderDTO orderDTO = orderDao.selectOrderDTO(orderId);
		validateUnableToCancelOrder(orderDTO);
		orderDao.updateOrderStatusCancel(orderId);
	}

	@Override
	public void isCorrectUserOrder(long orderId, String userId) {
		OrderDTO orderDTO = orderDao.selectOrderDTO(orderId);
		if (!userId.equals(orderDTO.getUserId())) {
			throw new IllegalArgumentException("잘못된 입력입니다.");
		}
	}

	private void registerOrderMenus(List<OrderItemDTO> orderItemDTOS) {
		orderDao.insertOrderMenus(orderItemDTOS);
	}

	private long getTotalPrice(String userId) {

		List<CartlistDTO> cartlistDTOS = cartDao.getCartList(userId);
		List<Long> cartMenuIds = cartlistDTOS.stream().map(
			CartlistDTO::getMenuId).collect(Collectors.toList());

		int menuCount = menuDao.getMenuCount(cartMenuIds);
		if (cartMenuIds.size() != menuCount) {
			throw new MenuIdEmptyException("주문하실 메뉴를 다시 확인해 주세요.");
		}

		List<Long> menuPriceList = menuDao.getMenuPriceList(cartMenuIds);

		long totalPrice = 0;

		List<Long> menuAmountList = cartlistDTOS.stream().map(
			CartlistDTO::getAmount).collect(Collectors.toList());

		for (int i = 0; i < menuPriceList.size(); i++) {
			long menuPrice = menuPriceList.get(i);
			long menuAmount = menuAmountList.get(i);

			totalPrice += menuPrice * menuAmount;
		}

		return totalPrice;

	}

	private List<OrderItemDTO> getOrderItemDTO(String userId, Long orderId) {

		List<CartlistDTO> cartlistDTOS = cartDao.getCartList(userId);

		return cartlistDTOS.stream().map(x ->
			new OrderItemDTO(x.getMenuName(), x.getPrice(), x.getAmount(), orderId)
		).collect(Collectors.toList());
	}

	private void validateUnableToCancelOrder(OrderDTO orderDTO) {
		if (orderDTO.getOrderStatus() == OrderStatus.ORDER_CANCEL) {
			throw new IllegalStateException("가게 요청에 의해 취소된 주문입니다.");
		} else if (orderDTO.getOrderStatus() != OrderStatus.ORDER_COMPLETE) {
			throw new IllegalStateException("취소 불가능한 주문입니다.");
		}
	}
}