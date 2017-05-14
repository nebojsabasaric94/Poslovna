package bank.dailyAccountBalance;

import java.util.List;

public interface DailyAccountBalanceService {
	List<DailyAccountBalance> findAll();

	DailyAccountBalance save(DailyAccountBalance dailyAccountBalance);

	DailyAccountBalance findOne(Long id);
}
