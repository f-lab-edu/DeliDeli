package flab.delideli.dao;

import flab.delideli.dto.OrderDTO;
import flab.delideli.dto.OrderItemDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface OrderDao {

	Long insertOrder(OrderDTO orderDTO);

	void insertOrderMenus(List<OrderItemDTO> orderItemDTOS);

}
