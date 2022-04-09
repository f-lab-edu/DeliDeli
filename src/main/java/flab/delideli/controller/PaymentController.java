package flab.delideli.controller;

import flab.delideli.annotation.CurrentUser;
import flab.delideli.dto.RequestPaymentDTO;
import flab.delideli.service.PaymentService;
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
@Api(tags = {"결제 컨트롤러 API"})
@AllArgsConstructor
@RequestMapping("/{orderId}/payment")
public class PaymentController {

	private PaymentService paymentService;

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	@ApiOperation(value = "결제")
	public void pay(@PathVariable("orderId") Long orderId, @CurrentUser String userId,
		@RequestBody @Valid RequestPaymentDTO requestPaymentDTO) {
		paymentService.pay(orderId, userId, requestPaymentDTO);
	}

}