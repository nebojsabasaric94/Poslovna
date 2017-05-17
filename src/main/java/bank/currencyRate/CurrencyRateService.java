package bank.currencyRate;

import java.util.List;

public interface CurrencyRateService {
	public List<CurrencyRate> findAll();

	public CurrencyRate save(CurrencyRate currencyRate);

	public CurrencyRate findOne(Long id);
	
	public void delete(Long id);
	
	public List<CurrencyRate> search(CurrencyRate currencyRate);
}
