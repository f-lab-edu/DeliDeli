package flab.delideli.controller;

import flab.delideli.annotation.CurrentUser;
import flab.delideli.annotation.LoginUserLevel;
import flab.delideli.enums.UserLevel;
import flab.delideli.service.OrderOwnerService;
import flab.delideli.service.OwnerService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/owners/orders")
public class OrderOwnerController {

	private OwnerService ownerService;

	@PostMapping
	@LoginUserLevel(role = UserLevel.OWNER_LEVEL)
	public void updateOrderStatus(long orderId, @CurrentUser String userId) {
		ownerService.updateOrderStatusCooking(orderId, userId);
	}
}
