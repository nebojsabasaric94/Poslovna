package bank.exchageRateList;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
@Transactional
public class ExchangeRateListServiceImpl implements ExchangeRateListService {
	private final ExchangeRateListRepository repository;

	@Autowired
	public ExchangeRateListServiceImpl(final ExchangeRateListRepository repository) {
		this.repository = repository;
	}

	@Override
	public List<ExchangeRateList> findAll() {
		return (List<ExchangeRateList>) repository.findAll();
	}

	@Override
	public ExchangeRateList save(ExchangeRateList exchangeRateList) {
		return repository.save(exchangeRateList);
	}

	@Override
	public ExchangeRateList findOne(Long id) {
		return repository.findOne(id);
	}
	
	@Override
	public void delete(Long id) {
		repository.delete(id);
	}
}