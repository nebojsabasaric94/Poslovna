package bank.exchageRateList;

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

import bank.dailyAccountBalance.DailyAccountBalance;

@RestController
@RequestMapping("/exchangeRateList")
public class ExchangeRateListController {

	private final ExchangeRateListService exchangeRateListService;
	
	

	@Autowired
	public ExchangeRateListController(final ExchangeRateListService service) {
		this.exchangeRateListService = service;
	}

	@GetMapping
	public ResponseEntity<List<ExchangeRateList>> findAll() {
		return new ResponseEntity<>(exchangeRateListService.findAll(), HttpStatus.OK);
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public void save(@RequestBody ExchangeRateList exchangeRateList) {
		exchangeRateList.setId(null);
		exchangeRateListService.save(exchangeRateList);
	}
	

	@GetMapping("/deleteExchangeRateList/{id}")
	public List<ExchangeRateList> deleteBank(@PathVariable Long id){
		exchangeRateListService.delete(id);
		
		return exchangeRateListService.findAll();
	}
	
	
}