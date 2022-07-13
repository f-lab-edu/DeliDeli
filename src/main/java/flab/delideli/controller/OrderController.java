package flab.delideli.controller;

import flab.delideli.annotation.CurrentUser;
import flab.delideli.dto.RequestOrderDTO;
import flab.delideli.service.OrderUserService;
import flab.delideli.service.payment.CommonPaymentService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import javax.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Api(tags = {"주문 컨트롤러 API"})
@AllArgsConstructor
@RequestMapping("/orders")
public class OrderController {

	private OrderUserService orderService;
	private CommonPaymentService commonPaymentService;

	@PostMapping()
	@ResponseStatus(HttpStatus.CREATED)
	@ApiOperation(value = "주문 등록")
	public void registerOrder(@RequestBody @Valid RequestOrderDTO requestOrderDTO, @CurrentUser String userId) {
		orderService.registerOrder(requestOrderDTO, userId);
	}

	@PostMapping(value = "/{orderId}/cancel")
	@ApiOperation(value = "주문 취소")
	public void cancelOrder(@CurrentUser String userId, @PathVariable long orderId) {
		orderService.isCorrectUserOrder(orderId, userId);
		orderService.cancelOrder(orderId, userId);
		commonPaymentService.cancelPayment(commonPaymentService.getPaymentId(orderId), userId);
	}
}
