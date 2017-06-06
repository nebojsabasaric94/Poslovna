package bank.currency;

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

import bank.country.Country;
import bank.country.CountryService;

@RestController
@RequestMapping("/currency")
public class CurrencyController {

	private final CurrencyService currencyService;
	private final CountryService countryService;
	

	@Autowired
	public CurrencyController(final CurrencyService service, final CountryService countryService) {
		this.currencyService = service;
		this.countryService = countryService;
	}

	@GetMapping
	public ResponseEntity<List<Currency>> findAll() {
		return new ResponseEntity<>(currencyService.findAll(), HttpStatus.OK);
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public void save(@RequestBody Currency currency) {
		currency.setId(null);
		currencyService.save(currency);
	}
	
	@GetMapping("/deleteCurrency/{id}")
	public List<Currency> deleteBank(@PathVariable Long id){
		currencyService.delete(id);
		
		return currencyService.findAll();
	}
	
	@PostMapping("/search")
	public List<Currency> search(@RequestBody Currency currency){
		return currencyService.search(currency);
	}
	
	@GetMapping("/nextCurrencies/{countryId}")
	public List<Currency> getNextExhangeRateList(@PathVariable Long countryId){
		
		Country country = countryService.findOne(countryId);
		List<Currency> currencyList = country.getCurrencies();
		
		return currencyList;
		
	}
	
	@PutMapping("/update")
	public  Currency update(@RequestBody Currency c) {
		return currencyService.save(c);
	}
}