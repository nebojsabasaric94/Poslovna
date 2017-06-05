package bank.dailyAccountBalance;

import java.util.Date;
import java.util.List;

import bank.legalEntityAccount.LegalEntityAccount;

public interface DailyAccountBalanceService {
	List<DailyAccountBalance> findAll();

	DailyAccountBalance save(DailyAccountBalance dailyAccountBalance);

	DailyAccountBalance findOne(Long id);
	
	public void delete(Long id);
	
	public List<DailyAccountBalance> search(DailyAccountBalance accountBalance);

	DailyAccountBalance findByAccountNumberAndDate(LegalEntityAccount creditorAccount,Date date);
}
