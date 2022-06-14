package flab.delideli.service;

public interface OwnerService {

	void updateOrderStatusCooking(long orderId, String userId);

	void updateOrderStatusCancel(long orderId, String userId);

	void updateOrderStatusCookingComplete(long orderId, String userId);

}
