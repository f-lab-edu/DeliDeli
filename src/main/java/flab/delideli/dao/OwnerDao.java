package flab.delideli.dao;

import flab.delideli.dto.OrderDTO;
import flab.delideli.enums.OrderStatus;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface OwnerDao {
	void updateOrderStatusCooking(long orderId);

	long getShopIdInOrders(long orderId);

	void updateOrderStatusCancel(long orderId);

	void updateOrderStatusCookingComplete(long orderId);

	OrderDTO selectOrderDTO(long orderId);
}
