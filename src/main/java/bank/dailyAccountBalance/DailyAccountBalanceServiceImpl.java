package bank.dailyAccountBalance;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
@Transactional
public class DailyAccountBalanceServiceImpl implements DailyAccountBalanceService {
	private final DailyAccountBalanceRepository repository;

	@Autowired
	public DailyAccountBalanceServiceImpl(final DailyAccountBalanceRepository repository) {
		this.repository = repository;
	}

	@Override
	public List<DailyAccountBalance> findAll() {
		return (List<DailyAccountBalance>) repository.findAll();
	}

	@Override
	public DailyAccountBalance save(DailyAccountBalance dailyAccountBalance) {
		return repository.save(dailyAccountBalance);
	}

	@Override
	public DailyAccountBalance findOne(Long id) {
		return repository.findOne(id);
	}
	
	@Override
	public void delete(Long id) {
		repository.delete(id);
	}
}