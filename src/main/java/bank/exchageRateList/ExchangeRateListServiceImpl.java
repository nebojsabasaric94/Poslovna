package bank.exchageRateList;

import java.sql.Date;
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

	@Override
	public List<ExchangeRateList> search(ExchangeRateList exchangeRateList) {
		String bankId = "%";
		if(exchangeRateList.getCommercialBankRate() != null)
			bankId = "" + exchangeRateList.getCommercialBankRate().getId();
		
		String date ="" ;
		if(exchangeRateList.getDate() != null){
			date =new Date(exchangeRateList.getDate().getTime()).toString();
		}
		
		String appliedBy = "";
		if(exchangeRateList.getAppliedBy() != null){
			appliedBy = new Date(exchangeRateList.getAppliedBy().getTime()).toString();
		}		
		
		String number = "%";
		if(exchangeRateList.getNumberOfExchangeRateList() > 0)
			number = ""+exchangeRateList.getNumberOfExchangeRateList();
		
		return repository.search(bankId, date, number, appliedBy);
	}
}