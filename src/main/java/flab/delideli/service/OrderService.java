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
import java.util.ArrayList;
import java.util.List;
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
			long menuIdInt = cartlistDTOS.get(i).getMenuId();
			Long menuId = Long.valueOf(menuIdInt);
			String menuName = cartlistDTOS.get(i).getMenuName();
			int menuPrice;

			boolean isExistMenuId = menuDao.isExistMenuId(menuId);
			if (isExistMenuId) {
				menuPrice = menuDao.getMenuPrice(menuId);
			} else {
				throw new MenuIdEmptyException("주문하신 메뉴(" + menuName + ")는 존재하지 않습니다.");
			}

			int menuAmount = cartlistDTOS.get(i).getAmount();
			totalPrice = menuPrice * menuAmount;
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