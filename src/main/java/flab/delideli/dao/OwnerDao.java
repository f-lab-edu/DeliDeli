package flab.delideli.dao;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface OwnerDao {
	void updateOrderStatusCooking(long orderId);
}
