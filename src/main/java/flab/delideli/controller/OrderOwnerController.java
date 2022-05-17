package flab.delideli.controller;

import flab.delideli.annotation.CurrentUser;
import flab.delideli.annotation.LoginUserLevel;
import flab.delideli.enums.UserLevel;
import flab.delideli.service.OrderOwnerService;
import flab.delideli.service.OwnerService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/owners/orders/{orderId}")
public class OrderOwnerController {

	private final OwnerService ownerService;

	@PostMapping
	@LoginUserLevel(role = UserLevel.OWNER_LEVEL)
	public void updateOrderStatusOK(@PathVariable long orderId, @CurrentUser String userId) {
		ownerService.updateOrderStatusCooking(orderId, userId);
	}

	@PostMapping("/cancel")
	@LoginUserLevel(role = UserLevel.OWNER_LEVEL)
	public void updateOrderStatusCancel(@PathVariable long orderId, @CurrentUser String userId) {
		ownerService.updateOrderStatusCancel(orderId, userId);
	}
}
