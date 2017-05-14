package bank.paymentType;

import java.util.List;

public interface PaymentTypeService {
	List<PaymentType> findAll();

	PaymentType save(PaymentType paymentType);

	PaymentType findOne(Long id);
}
