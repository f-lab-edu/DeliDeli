package flab.delideli.controller;

import flab.delideli.annotation.CurrentUser;
import flab.delideli.annotation.LoginUserLevel;
import flab.delideli.enums.UserLevel;
import flab.delideli.service.OrderOwnerService;
import flab.delideli.service.OwnerService;
import lombok.RequiredArgsConstructor;
import org.apache.catalina.User;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/owners/orders/{orderId}")
public class OrderOwnerController {

	private final OwnerService ownerService;

	@PatchMapping
	@LoginUserLevel(role = UserLevel.OWNER_LEVEL)
	public void updateOrderStatusOK(@PathVariable long orderId, @CurrentUser String userId) {
		ownerService.updateOrderStatusCooking(orderId, userId);
	}

	@PatchMapping("/cancel")
	@LoginUserLevel(role = UserLevel.OWNER_LEVEL)
	public void updateOrderStatusCancel(@PathVariable long orderId, @CurrentUser String userId) {
		ownerService.updateOrderStatusCancel(orderId, userId);
	}

	@PatchMapping("/complete")
	@LoginUserLevel(role = UserLevel.OWNER_LEVEL)
	public void updateOrderStatusComplete(@PathVariable long orderId, @CurrentUser String userId) {
		ownerService.updateOrderStatusCookingComplete(orderId, userId);
	}
}
