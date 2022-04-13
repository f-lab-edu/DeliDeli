package flab.delideli.controller;

import flab.delideli.annotation.CurrentUser;
import flab.delideli.dto.RequestPaymentDTO;
import flab.delideli.service.payment.ContactPaymentStrategy;
import flab.delideli.service.payment.CreditCardStrategy;
import flab.delideli.service.payment.DepositStrategy;
import flab.delideli.service.payment.KakaoPayStrategy;
import flab.delideli.service.payment.PaymentService;
import flab.delideli.service.payment.PaymentStrategy;
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

	private CreditCardStrategy creditCardStrategy;
	private DepositStrategy depositStrategy;
	private KakaoPayStrategy kakaoPayStrategy;
	private ContactPaymentStrategy contactPaymentStrategy;
	private PaymentService paymentService;

	@PostMapping("/credit_card")
	@ResponseStatus(HttpStatus.CREATED)
	@ApiOperation(value = "카드로 결제")
	public void payCreditCard(@PathVariable("orderId") Long orderId, @CurrentUser String userId,
		@RequestBody @Valid RequestPaymentDTO requestPaymentDTO) {
		paymentService.pay(orderId, userId, requestPaymentDTO, creditCardStrategy);
	}

	@PostMapping("/deposit")
	@ResponseStatus(HttpStatus.CREATED)
	@ApiOperation(value = "계좌이체로 결제")
	public void payDeposit(@PathVariable("orderId") Long orderId, @CurrentUser String userId,
		@RequestBody @Valid RequestPaymentDTO requestPaymentDTO) {
		paymentService.pay(orderId, userId, requestPaymentDTO, depositStrategy);
	}

	@PostMapping("/kakaopay")
	@ResponseStatus(HttpStatus.CREATED)
	@ApiOperation(value = "카카오페이로 결제")
	public void payKakaoPay(@PathVariable("orderId") Long orderId, @CurrentUser String userId,
		@RequestBody @Valid RequestPaymentDTO requestPaymentDTO) {
		paymentService.pay(orderId, userId, requestPaymentDTO, kakaoPayStrategy);
	}

	@PostMapping("/contact_pay")
	@ResponseStatus(HttpStatus.CREATED)
	@ApiOperation(value = "만나서 결제")
	public void payContact(@PathVariable("orderId") Long orderId, @CurrentUser String userId,
		@RequestBody @Valid RequestPaymentDTO requestPaymentDTO) {
		paymentService.pay(orderId, userId, requestPaymentDTO, contactPaymentStrategy);
	}

}
