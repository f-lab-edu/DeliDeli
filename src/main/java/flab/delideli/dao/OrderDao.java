package flab.delideli.dao;

import flab.delideli.dto.OrderDTO;
import flab.delideli.dto.OrderItemDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface OrderDao {

	long insertOrder(OrderDTO orderDTO);

	void insertOrderMenus(List<OrderItemDTO> orderItemDTOS);

	OrderDTO selectOrderDTO(long orderId);

	void updateOrderStatusCancel(long orderId);

	void updateOrderStatusCooking(long orderId);

	long getShopIdInOrders(long orderId);

	void updateOrderStatusCookingComplete(long orderId);

}
