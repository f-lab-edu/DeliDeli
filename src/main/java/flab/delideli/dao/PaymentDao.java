package flab.delideli.dao;

import flab.delideli.dto.PaymentDTO;
import org.apache.ibatis.annotations.Mapper;

@Mapper

public interface PaymentDao {

	void insertPayment(PaymentDTO paymentDTO);

	PaymentDTO selectPaymentSummary(long paymentId, String userId);

	void updatePaymentStatusCanceled(long paymentId, String userId);

	void updatePaymentStatusUnableCancel(long orderId);

	void updatePaymentStatusCanceledByOwner(long orderId);

	long selectPaymentId(long orderId);
}
