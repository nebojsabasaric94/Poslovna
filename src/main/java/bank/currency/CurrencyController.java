package bank.currency;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/currency")
public class CurrencyController {

	private final CurrencyService currencyService;
	
	

	@Autowired
	public CurrencyController(final CurrencyService service) {
		this.currencyService = service;
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
	
	
}