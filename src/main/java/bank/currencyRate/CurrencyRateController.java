package bank.currencyRate;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import bank.bank.Bank;
import bank.currency.Currency;
import bank.currency.CurrencyService;
import bank.exchageRateList.ExchangeRateList;
import bank.exchageRateList.ExchangeRateListService;

@RestController
@RequestMapping("/currencyRate")
public class CurrencyRateController {

	private final CurrencyRateService currencyRateService;
	private final ExchangeRateListService exchangeRateListService;
	private final CurrencyService currencyService;
	
	@Autowired
	public CurrencyRateController(final CurrencyRateService service, final ExchangeRateListService exchangeRateListService, final CurrencyService currencyService) {
		this.currencyRateService = service;
		this.exchangeRateListService = exchangeRateListService;
		this.currencyService = currencyService;
	}

	@GetMapping
	public ResponseEntity<List<CurrencyRate>> findAll() {
		return new ResponseEntity<>(currencyRateService.findAll(), HttpStatus.OK);
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public void save(@RequestBody CurrencyRate currencyRate) {
		currencyRateService.save(currencyRate);
	}
	
	@GetMapping("/deleteCureencyRate/{id}")
	public List<CurrencyRate> deleteBank(@PathVariable Long id){
		currencyRateService.delete(id);
		
		return currencyRateService.findAll();
	}
	
	@PostMapping("/search")
	public List<CurrencyRate> search(@RequestBody CurrencyRate currencyRate){
		return currencyRateService.search(currencyRate);
	}
	
	@GetMapping("/nextCurrencyRate/{exchangeRateListId}")
	public List<CurrencyRate> getNextExhangeRateList(@PathVariable Long exchangeRateListId){
		
		ExchangeRateList exchangeRateList = exchangeRateListService.findOne(exchangeRateListId);
		List<CurrencyRate> currencyList = exchangeRateList.getCurrencyRates();
		
		return currencyList;
	}
	
	@GetMapping("/nextAccordingToCurrency/{accordingToCurrencyId}")
	public List<CurrencyRate> nextAccordingToCurrency(@PathVariable Long accordingToCurrencyId){
		Currency currency = currencyService.findOne(accordingToCurrencyId);
		
		return currency.getAccordingToCurrencyRate();
	}
	
	@GetMapping("/nextBaseCurrency/{baseCurrencyId}")
	public List<CurrencyRate> nextBaseCurrency(@PathVariable Long baseCurrencyId){
		Currency currency = currencyService.findOne(baseCurrencyId);
		
		return currency.getBaseCurrencyRate();
	}
	
	
	@PutMapping("/update")
	public  CurrencyRate update(@RequestBody CurrencyRate c) {
		return currencyRateService.save(c);
	}
}