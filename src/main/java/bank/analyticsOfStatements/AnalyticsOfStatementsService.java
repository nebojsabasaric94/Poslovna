package bank.analyticsOfStatements;

import java.util.ArrayList;
import java.util.List;

import bank.legalEntityAccount.LegalEntityAccount;

public interface AnalyticsOfStatementsService {
	List<AnalyticsOfStatements> findAll();

	public AnalyticsOfStatements save(AnalyticsOfStatements analyticsOfStatements);

 	public AnalyticsOfStatements findOne(Long id);
 	
 	public List<AnalyticsOfStatements> search(AnalyticsOfStatements analyticsOfStatements);

	ArrayList<AnalyticsOfStatements> findByDateAndAccount(LegalEntityAccount legalEntityAccount,
			java.util.Date trafficDate);
}
