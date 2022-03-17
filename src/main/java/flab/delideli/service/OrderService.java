package flab.delideli.service;

import flab.delideli.dao.CartDao;
import flab.delideli.dao.OrderDao;
import flab.delideli.dto.CartlistDTO;
import flab.delideli.dto.OrderDTO;
import flab.delideli.dto.OrderItemDTO;
import flab.delideli.dto.RequestOrderDTO;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderService {

	private final OrderDao orderDao;
	private final CartDao cartDao;

	public void registerOrder(RequestOrderDTO requestOrderDTO, String userId) {

		OrderDTO orderDTO = new OrderDTO(userId, requestOrderDTO.getAddress(),
			requestOrderDTO.getPhoneNumber(), requestOrderDTO.getPaymentMethod(),
			getTotalPrice(userId), requestOrderDTO.getShopId(),
			requestOrderDTO.getRequest(), LocalDate.now());

		Long orderId = orderDao.insertOrder(orderDTO);

		List<OrderItemDTO> orderItemDTOS =
			getOrderItemDTO(userId, orderId);

		registerOrderMenus(orderItemDTOS);

	}

	public void registerOrderMenus(List<OrderItemDTO> orderItemDTOS) {
		orderDao.insertOrderMenus(orderItemDTOS);
	}

	public int getTotalPrice(String userId) {

		List<CartlistDTO> cartlistDTOS = cartDao.getCartList(userId);
		int totalPrice = 0;

		for (int i = 0; i < cartlistDTOS.size(); i++) {
			int price = cartlistDTOS.get(i).getPrice();
			int amount = cartlistDTOS.get(i).getAmount();
			totalPrice = price * amount;
		}

		return totalPrice;

	}

	public List<OrderItemDTO> getOrderItemDTO(String userId, Long orderId) {

		List<CartlistDTO> cartlistDTOS = cartDao.getCartList(userId);
		List<OrderItemDTO> orderItemDTOS = new ArrayList<>();

		for (int i = 0; i < cartlistDTOS.size(); i++) {
			String menuName = cartlistDTOS.get(i).getMenuName();
			int price = cartlistDTOS.get(i).getPrice();
			int amount = cartlistDTOS.get(i).getAmount();

			OrderItemDTO orderItemDTO = new OrderItemDTO(menuName, price, amount, orderId);
			orderItemDTOS.add(orderItemDTO);
		}

		return orderItemDTOS;
	}
}
