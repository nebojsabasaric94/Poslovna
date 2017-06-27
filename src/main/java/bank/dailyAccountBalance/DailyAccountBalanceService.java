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
	public List<DailyAccountBalance> findBalances(LegalEntityAccount account,Date start,Date end);

	DailyAccountBalance findByAccountNumberAndDate(LegalEntityAccount creditorAccount,Date date);

	DailyAccountBalance findAccountStateAt(LegalEntityAccount creditorAccount, Date date);

}
