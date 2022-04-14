package flab.delideli.service.payment;

import flab.delideli.enums.PaymentType;
import flab.delideli.exception.PaymentFailureException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PaymentFactory {

	private final ContactPaymentService contactPayment;
	private final CreditCardService creditCard;
	private final DepositService deposit;
	private final KakaoPayService kakaoPay;

	public PaymentService getType(PaymentType paymentType) {

		final PaymentService paymentService;

		switch (paymentType) {
			case CONTACT_PAYMENT:
				paymentService = contactPayment;
				break;
			case CREDIT_CARD:
				paymentService = creditCard;
				break;
			case DEPOSIT:
				paymentService = deposit;
				break;
			case KAKAO_PAY:
				paymentService = kakaoPay;
				break;
			default:
				throw new PaymentFailureException("결제 수단을 확인해주세요.");
		}

		return paymentService;

	}

}