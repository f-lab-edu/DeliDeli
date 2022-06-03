package flab.delideli.controller;

import flab.delideli.annotation.CurrentUser;
import flab.delideli.annotation.UserAuthorization;
import flab.delideli.enums.UserLevel;
import flab.delideli.service.OwnerService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/owners/orders/{orderId}")
public class OrderOwnerController {

	private final OwnerService ownerService;

	@PatchMapping
	@UserAuthorization(role = UserLevel.OWNER)
	public void updateOrderStatusOK(@PathVariable long orderId, @CurrentUser String userId) {
		ownerService.updateOrderStatusCooking(orderId, userId);
	}

	@PatchMapping("/cancel")
	@UserAuthorization(role = UserLevel.OWNER)
	public void updateOrderStatusCancel(@PathVariable long orderId, @CurrentUser String userId) {
		ownerService.updateOrderStatusCancel(orderId, userId);
	}

	@PatchMapping("/complete")
	@UserAuthorization(role = UserLevel.OWNER)
	public void updateOrderStatusComplete(@PathVariable long orderId, @CurrentUser String userId) {
		ownerService.updateOrderStatusCookingComplete(orderId, userId);
	}
}
