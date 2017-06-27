package bank.paymentType;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
@Transactional
public class PaymentTypeServiceImpl implements PaymentTypeService {
	private final PaymentTypeRepository repository;

	@Autowired
	public PaymentTypeServiceImpl(final PaymentTypeRepository repository) {
		this.repository = repository;
	}

	@Override
	public List<PaymentType> findAll() {
		return (List<PaymentType>) repository.findAll();
	}

	@Override
	public PaymentType save(PaymentType paymentType) {
		return repository.save(paymentType);
	}

	@Override
	public PaymentType findOne(Long id) {
		return repository.findOne(id);
	}
	
	@Override
	public void delete(Long id) {
		repository.delete(id);
	}

	@Override
	public List<PaymentType> search(PaymentType paymentType) {

		return repository.search(paymentType.getCode(), paymentType.getNameOfPaymentType());
	}

	@Override
	public PaymentType findByName(String paymentType) {
		// TODO Auto-generated method stub
		return repository.findByName(paymentType);
	}
}