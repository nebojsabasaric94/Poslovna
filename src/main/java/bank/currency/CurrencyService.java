package bank.currency;

import java.util.List;

public interface CurrencyService {
	public List<Currency> findAll();

	public Currency save(Currency currency);

	public Currency findOne(Long id);
	
	public void delete(Long id);
	
	public List<Currency> search(Currency currency);
	
	public Currency findByCode(String code);
}
