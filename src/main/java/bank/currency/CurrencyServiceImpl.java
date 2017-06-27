package bank.currency;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
@Transactional
public class CurrencyServiceImpl implements CurrencyService {
	private final CurrencyRepository repository;

	@Autowired
	public CurrencyServiceImpl(final CurrencyRepository repository) {
		this.repository = repository;
	}

	@Override
	public List<Currency> findAll() {
		return (List<Currency>) repository.findAll();
	}

	@Override
	public Currency save(Currency currency) {
		return repository.save(currency);
	}

	@Override
	public Currency findOne(Long id) {
		return repository.findOne(id);
	}

	@Override
	public void delete(Long id) {
		repository.delete(id);
	}

	@Override
	public List<Currency> search(Currency currency) {
		String country_id = "%";
		if(currency.getCountry() != null)
			country_id = "" + currency.getCountry().getId() ;
		return repository.search(currency.getName(), country_id, currency.getOfficial_code(), currency.getDomicilna());
	}

	@Override
	public Currency findByCode(String code) {
		// TODO Auto-generated method stub
		return repository.findByCode(code);
	}
}