package flab.delideli.service;

import flab.delideli.dto.RequestOrderDTO;

public interface OrderService {

	void registerOrder(RequestOrderDTO requestOrderDTO, String userId);

	void cancelOrder(long orderId, String userId);

	void isCorrectUserOrder(long orderId, String userId);
}
