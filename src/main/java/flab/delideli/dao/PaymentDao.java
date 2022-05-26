package flab.delideli.dao;

import flab.delideli.dto.PaymentDTO;

public interface PaymentDao {

	void insertPayment(PaymentDTO paymentDTO);

	PaymentDTO selectPaymentSummary(long paymentId, String userId);

	void updatePaymentStatusCanceled(long paymentId, String userId);

}