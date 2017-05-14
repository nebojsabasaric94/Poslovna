package bank.analyticsOfStatements;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
@Transactional
public class AnalyticsOfStatementsServiceImpl implements AnalyticsOfStatementsService {
	private final AnalyticsOfStatementsRepository repository;

	@Autowired
	public AnalyticsOfStatementsServiceImpl(final AnalyticsOfStatementsRepository repository) {
		this.repository = repository;
	}

	@Override
	public List<AnalyticsOfStatements> findAll() {
		return (List<AnalyticsOfStatements>) repository.findAll();
	}

	@Override
	public AnalyticsOfStatements save(AnalyticsOfStatements analyticsOfStatements) {
		return repository.save(analyticsOfStatements);
	}

	@Override
	public AnalyticsOfStatements findOne(Long id) {
		return repository.findOne(id);
	}
}