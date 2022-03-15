package flab.delideli.dao;

import flab.delideli.dto.OrderDTO;
import flab.delideli.dto.OrderItemDTO;
import java.util.List;

public interface OrderDao {

	void insertOrder(OrderDTO orderDTO);

	void insertOrderMenus(List<OrderItemDTO> orderItemDTOS);

}
