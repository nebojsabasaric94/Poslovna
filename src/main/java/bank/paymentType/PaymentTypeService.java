package bank.paymentType;

import java.util.List;

public interface PaymentTypeService {
	List<PaymentType> findAll();

	PaymentType save(PaymentType paymentType);

	PaymentType findOne(Long id);
	
	public void delete(Long id);
	
	public List<PaymentType> search(PaymentType paymentType);

	PaymentType findByName(String paymentType);
}
