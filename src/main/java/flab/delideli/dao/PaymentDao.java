package flab.delideli.dao;

import flab.delideli.dto.PaymentDTO;
import java.util.List;

public interface PaymentDao {

	void insertPayment(PaymentDTO paymentDTO);

	PaymentDTO selectPaymentSummary(long paymentId, String userId);

	List<Long> selectAllPaymentSummariesOfUser(String userId);

}
