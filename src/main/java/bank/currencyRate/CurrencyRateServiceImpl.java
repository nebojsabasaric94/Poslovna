package bank.currencyRate;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
@Transactional
public class CurrencyRateServiceImpl implements CurrencyRateService {
	private final CurrencyRateRepository repository;

	@Autowired
	public CurrencyRateServiceImpl(final CurrencyRateRepository repository) {
		this.repository = repository;
	}

	@Override
	public List<CurrencyRate> findAll() {
		return (List<CurrencyRate>) repository.findAll();
	}

	@Override
	public CurrencyRate save(CurrencyRate currencyRate) {
		return repository.save(currencyRate);
	}

	@Override
	public CurrencyRate findOne(Long id) {
		return repository.findOne(id);
	}
}