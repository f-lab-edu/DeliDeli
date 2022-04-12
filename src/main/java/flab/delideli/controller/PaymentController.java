package flab.delideli.controller;

import flab.delideli.annotation.CurrentUser;
import flab.delideli.dto.RequestPaymentDTO;
import flab.delideli.service.payment.ContactPaymentService;
import flab.delideli.service.payment.CreditCardService;
import flab.delideli.service.payment.DepositService;
import flab.delideli.service.payment.KakaoPayService;
import flab.delideli.service.payment.PaymentService;
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
	private CreditCardService creditCardService;
	private DepositService depositService;
	private KakaoPayService kakaoPayService;
	private ContactPaymentService contactPaymentService;

	@PostMapping("/credit_card")
	@ResponseStatus(HttpStatus.CREATED)
	@ApiOperation(value = "카드로 결제")
	public void payCreditCard(@PathVariable("orderId") Long orderId, @CurrentUser String userId,
		@RequestBody @Valid RequestPaymentDTO requestPaymentDTO) {
		creditCardService.pay(orderId, userId, requestPaymentDTO);
	}

	@PostMapping("/deposit")
	@ResponseStatus(HttpStatus.CREATED)
	@ApiOperation(value = "계좌이체로 결제")
	public void payDeposit(@PathVariable("orderId") Long orderId, @CurrentUser String userId,
		@RequestBody @Valid RequestPaymentDTO requestPaymentDTO) {
		depositService.pay(orderId, userId, requestPaymentDTO);
	}

	@PostMapping("/kakaopay")
	@ResponseStatus(HttpStatus.CREATED)
	@ApiOperation(value = "카카오페이로 결제")
	public void payKakaoPay(@PathVariable("orderId") Long orderId, @CurrentUser String userId,
		@RequestBody @Valid RequestPaymentDTO requestPaymentDTO) {
		kakaoPayService.pay(orderId, userId, requestPaymentDTO);
	}

	@PostMapping("/contact_pay")
	@ResponseStatus(HttpStatus.CREATED)
	@ApiOperation(value = "만나서 결제")
	public void payContact(@PathVariable("orderId") Long orderId, @CurrentUser String userId,
		@RequestBody @Valid RequestPaymentDTO requestPaymentDTO) {
		contactPaymentService.pay(orderId, userId, requestPaymentDTO);
	}

}
