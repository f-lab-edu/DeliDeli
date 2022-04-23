package flab.delideli.controller;

import flab.delideli.annotation.CurrentUser;
import flab.delideli.annotation.LoginUserLevel;
import flab.delideli.dto.PaymentDTO;
import flab.delideli.dto.RequestPaymentDTO;
import flab.delideli.enums.UserLevel;
import flab.delideli.service.OrderService;
import flab.delideli.service.payment.CommonPaymentService;
import flab.delideli.service.payment.PaymentFactory;
import flab.delideli.service.payment.PaymentService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Api(tags = {"결제 컨트롤러 API"})
@AllArgsConstructor
@RequestMapping("/payments")
public class PaymentController {

	private final PaymentFactory paymentFactory;
	private final CommonPaymentService commonPaymentService;
	private final OrderService orderService;

	@PostMapping("/{orderId}")
	@ResponseStatus(HttpStatus.CREATED)
	@ApiOperation(value = "결제")
	public void pay(@PathVariable("orderId") Long orderId, @CurrentUser String userId,
		@RequestBody @Valid RequestPaymentDTO requestPaymentDTO) {
		final PaymentService paymentService = paymentFactory
			.getType(requestPaymentDTO.getPaymentType());

		paymentService.pay(orderId, userId, requestPaymentDTO);
	}

	@GetMapping("/{orderId}")
	@ApiOperation(value = "나(유저)의 특정 주문에 대한 결제내역 조회")
	@LoginUserLevel(role = UserLevel.MEMBER_LEVEL)
	public PaymentDTO getPaymentSummary(@PathVariable("orderId") long orderId,
		@CurrentUser String userId) {
		orderService.doesOrderIdAndUserIdExist(orderId, userId);

		return commonPaymentService.getPaymentSummary(orderId, userId);
	}

}