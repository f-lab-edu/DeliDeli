package flab.delideli.service;

import flab.delideli.dao.CartDao;
import flab.delideli.dao.MenuDao;
import flab.delideli.dao.OrderDao;
import flab.delideli.dto.CartlistDTO;
import flab.delideli.dto.OrderDTO;
import flab.delideli.dto.OrderItemDTO;
import flab.delideli.dto.RequestOrderDTO;
import flab.delideli.exception.MenuIdEmptyException;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderService {

	private final OrderDao orderDao;
	private final CartDao cartDao;
	private final MenuDao menuDao;

	public void registerOrder(RequestOrderDTO requestOrderDTO, String userId) {

		OrderDTO orderDTO = new OrderDTO(userId, requestOrderDTO.getAddress(),
			requestOrderDTO.getPhoneNumber(), requestOrderDTO.getPaymentMethod(),
			getTotalPrice(userId), requestOrderDTO.getShopId(),
			requestOrderDTO.getRequest(), LocalDate.now());

		long orderId = orderDao.insertOrder(orderDTO);

		List<OrderItemDTO> orderItemDTOS =
			getOrderItemDTO(userId, orderId);

		registerOrderMenus(orderItemDTOS);

	}

	public void registerOrderMenus(List<OrderItemDTO> orderItemDTOS) {
		orderDao.insertOrderMenus(orderItemDTOS);
	}

	public Long getTotalPrice(String userId) {

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

	public List<OrderItemDTO> getOrderItemDTO(String userId, Long orderId) {

		List<CartlistDTO> cartlistDTOS = cartDao.getCartList(userId);

		return cartlistDTOS.stream().map(x ->
			new OrderItemDTO(x.getMenuName(), x.getPrice(), x.getAmount(), orderId)
		).collect(Collectors.toList());
	}

}