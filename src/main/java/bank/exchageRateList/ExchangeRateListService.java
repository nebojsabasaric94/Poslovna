package bank.exchageRateList;

import java.util.List;

public interface ExchangeRateListService {
	List<ExchangeRateList> findAll();

	ExchangeRateList save(ExchangeRateList exchangeRateList);

	ExchangeRateList findOne(Long id);
}
