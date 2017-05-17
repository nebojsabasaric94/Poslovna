package bank.exchageRateList;

import java.util.List;

public interface ExchangeRateListService {
	List<ExchangeRateList> findAll();

	ExchangeRateList save(ExchangeRateList exchangeRateList);

	ExchangeRateList findOne(Long id);

	public void delete(Long id);
	
	public List<ExchangeRateList> search(ExchangeRateList exchangeRateList);
}
