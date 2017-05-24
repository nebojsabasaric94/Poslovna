package bank.currencyRate;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
@Transactional
public class CurrencyRateServiceImpl implements CurrencyRateService {
	private final CurrencyRateRepository repository;

	@Autowired
	public CurrencyRateServiceImpl(final CurrencyRateRepository repository) {
		this.repository = repository;
	}

	@Override
	public List<CurrencyRate> findAll() {
		return (List<CurrencyRate>) repository.findAll();
	}

	@Override
	public CurrencyRate save(CurrencyRate currencyRate) {
		return repository.save(currencyRate);
	}

	@Override
	public CurrencyRate findOne(Long id) {
		return repository.findOne(id);
	}

	@Override
	public void delete(Long id) {
		repository.delete(id);
	}

	@Override
	public List<CurrencyRate> search(CurrencyRate currencyRate) {

		String currencyInListId = "%";
		if(currencyRate.getCurrencyInList().getId() != null )
			currencyInListId = "" + currencyRate.getCurrencyInList().getId();

		String baseCurrency = "%";
		if(currencyRate.getBaseCurrency() != null)
			baseCurrency = "" + currencyRate.getBaseCurrency().getId();
		
		String accordingToCurrency = "%";
		if(currencyRate.getAccordingToCurrency() != null)
			accordingToCurrency = ""+currencyRate.getAccordingToCurrency().getId();
		
		
		
		String buyingExchangeRate =  "%";
		if(currencyRate.getBuyingExchangeRate() != null ){
			buyingExchangeRate = ""+currencyRate.getBuyingExchangeRate();
			String []splitted = buyingExchangeRate.split("\\.");
			if(splitted[1].equals("0"))
				buyingExchangeRate = splitted[0];
		}
		String sellingExchangeRate =  "%";
		if(currencyRate.getSellingExchangeRate() != null ){
			sellingExchangeRate = ""+currencyRate.getSellingExchangeRate();
			String []splitted = sellingExchangeRate.split("\\.");
			if(splitted[1].equals("0"))
				sellingExchangeRate = splitted[0];
		}
		String middleExchangeRate =  "%";
		if(currencyRate.getMiddleExchangeRate() != null){
			middleExchangeRate = ""+currencyRate.getMiddleExchangeRate();
			String []splitted = middleExchangeRate.split("\\.");
			if(splitted[1].equals("0"))
				middleExchangeRate = splitted[0];
		}
	
		
		return repository.search(baseCurrency, accordingToCurrency, currencyInListId, buyingExchangeRate, sellingExchangeRate, middleExchangeRate);
	}
}