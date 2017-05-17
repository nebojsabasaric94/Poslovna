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
		String id = "%";
		if(currencyRate.getId() != null)
			id  = "" + currencyRate.getId() ;
		String currencyInListId = "%";
		if(currencyRate.getCurrencyInList() != null && currencyRate.getCurrencyInList().getId() != null)
			currencyInListId = "" + currencyRate.getCurrencyInList().getId();
		
		String buyingExchangeRate =  "";
		if(currencyRate.getBuyingExchangeRate() > 0){
			buyingExchangeRate = ""+currencyRate.getBuyingExchangeRate();
			String []splitted = buyingExchangeRate.split("\\.");
			if(splitted[1].equals("0"))
				buyingExchangeRate = splitted[0];
		}
		String sellingExchangeRate =  "";
		if(currencyRate.getSellingExchangeRate() > 0){
			sellingExchangeRate = ""+currencyRate.getSellingExchangeRate();
			String[] splitted = sellingExchangeRate.split("\\.");
			if(splitted[1].equals("0"))
				sellingExchangeRate = splitted[0];
		}
		String middleExchangeRate =  "";
		if(currencyRate.getMiddleExchangeRate() > 0){
			middleExchangeRate = ""+currencyRate.getMiddleExchangeRate();
			String []splitted = middleExchangeRate.split("\\.");
			if(splitted[1].equals("0"))
				middleExchangeRate = splitted[0];
		}
		
		String baseCurrency = "";
		if(currencyRate.getBaseCurrency() != null)
			baseCurrency = "" + currencyRate.getBaseCurrency().getName();
		
		String accordingToCurrency = "";
		if(currencyRate.getAccordingToCurrency() != null)
			accordingToCurrency = ""+currencyRate.getAccordingToCurrency().getName();

		
		return repository.search(id, baseCurrency, accordingToCurrency, currencyInListId, buyingExchangeRate, sellingExchangeRate, middleExchangeRate);
	}
}