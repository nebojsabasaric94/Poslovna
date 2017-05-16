package bank.currencyRate;

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
@RequestMapping("/currencyRate")
public class CurrencyRateController {

	private final CurrencyRateService currencyRateService;
	
	

	@Autowired
	public CurrencyRateController(final CurrencyRateService service) {
		this.currencyRateService = service;
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
	
	
}