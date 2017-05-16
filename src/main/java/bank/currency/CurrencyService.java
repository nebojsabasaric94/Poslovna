package bank.currency;

import java.util.List;

public interface CurrencyService {
	List<Currency> findAll();

	Currency save(Currency currency);

	Currency findOne(Long id);
	
	public void delete(Long id);
}
