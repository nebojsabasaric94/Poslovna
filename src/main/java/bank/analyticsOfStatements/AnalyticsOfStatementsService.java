package bank.analyticsOfStatements;

import java.util.List;

public interface AnalyticsOfStatementsService {
	List<AnalyticsOfStatements> findAll();

	AnalyticsOfStatements save(AnalyticsOfStatements analyticsOfStatements);

	AnalyticsOfStatements findOne(Long id);
}
