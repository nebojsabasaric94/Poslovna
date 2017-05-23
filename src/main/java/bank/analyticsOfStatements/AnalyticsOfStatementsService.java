package bank.analyticsOfStatements;

import java.util.List;

public interface AnalyticsOfStatementsService {
	List<AnalyticsOfStatements> findAll();

	public AnalyticsOfStatements save(AnalyticsOfStatements analyticsOfStatements);

 	public AnalyticsOfStatements findOne(Long id);
 	
 	public List<AnalyticsOfStatements> search(AnalyticsOfStatements analyticsOfStatements);
}
