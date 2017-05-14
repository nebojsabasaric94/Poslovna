package bank.currencyRate;

import java.util.List;

public interface CurrencyRateService {
	List<CurrencyRate> findAll();

	CurrencyRate save(CurrencyRate currencyRate);

	CurrencyRate findOne(Long id);
}
